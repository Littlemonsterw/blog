package com.monster.blog.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.common.en.StatusEnum;
import com.monster.blog.common.utils.JwtTokenUtil;
import com.monster.blog.entity.User;
import com.monster.blog.mapper.UserMapper;
import com.monster.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wuhan
 * @date 2019/12/31 14:12
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public User register(User user) {
        user.setCreateTime(new Date());
        user.setStatus(StatusEnum.ACTIVE.getStatus());
        Integer count = baseMapper.selectCount(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (count > 0) {
            throw new ApiException("用户名已存在！");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.save(user);
        return user;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码输入不正确！");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public User getUsername(String username) {
        return baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
    }
}
