package com.monster.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.blog.entity.User;

/**
 * @author wuhan
 * @date 2019/12/31 14:12
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param user 用户注册信息
     * @return User
     */
    User register(User user);

    /**
     * 用户登陆
     * @param username 用户名
     * @param password 用户密码
     * @return JWT生成的token
     */
    String login(String username, String password);
}
