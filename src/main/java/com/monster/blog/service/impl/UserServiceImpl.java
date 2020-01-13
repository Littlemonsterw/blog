package com.monster.blog.service.impl;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.common.api.R;
import com.monster.blog.common.en.StatusEnum;
import com.monster.blog.common.utils.JwtTokenUtil;
import com.monster.blog.entity.User;
import com.monster.blog.mapper.UserMapper;
import com.monster.blog.service.RedisService;
import com.monster.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author wuhan
 * @date 2019/12/31 14:12
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String redisPrefixAuthCode;

    @Value("${redis.key.expire.authCode}")
    private Long authExpireSeconds;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

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
    public Map<String, String> login(String username, String password) {
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

        if (Objects.isNull(token)) {
            throw new ApiException("用户名或密码错误！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return tokenMap;
    }

    @Override
    public Map<String, String> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = jwtTokenUtil.refreshHeadToken(token);
        if (StrUtil.isEmpty(refreshToken)) {
            throw new ApiException("token已过期,请重新登录！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return tokenMap;
    }

    @Override
    public User getUsername(String username) {
        return baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
    }

    @Override
    public String generateAuthCode(String telephone) {
        StrBuilder strBuilder = new StrBuilder();
        Random random = new Random();
        int randomLength = 6;
        for (int i = 0; i < randomLength ; i++) {
            strBuilder.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到Redis
        redisService.set(redisPrefixAuthCode + telephone, strBuilder.toString());
        redisService.expire(redisPrefixAuthCode + telephone, authExpireSeconds);
        return strBuilder.toString();
    }

    @Override
    public R<String> verifyAuthCode(String telephone, String authCode) {
        if (ObjectUtil.isEmpty(authCode)) {
            return R.failed("请输入验证码");
        }
        String realAuthCode = redisService.get(redisPrefixAuthCode + telephone);
        boolean result = authCode.equals(realAuthCode);
        if (result) {
            return R.success("验证码校验成功！");
        } else {
            return R.validateFailed("验证码不正确或已过期！");
        }
    }
}
