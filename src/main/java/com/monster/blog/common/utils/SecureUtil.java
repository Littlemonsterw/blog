package com.monster.blog.common.utils;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.monster.blog.entity.User;
import com.monster.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 返回当前登录的用户信息
 * @author wuhan
 * @date 2020/1/17 15:13
 */
@Slf4j
@Component
public class SecureUtil {

    @Resource
    private  UserService userService;

    private static UserService staUserService;

    @PostConstruct
    public void init() {
        staUserService = this.userService;
    }

    public static String getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("当前登录用户：{}", userDetails.getUsername());
        return userDetails.getUsername();
    }

    public static User getCurrentUserObj() {
        return staUserService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, getCurrentUser()));
    }
}
