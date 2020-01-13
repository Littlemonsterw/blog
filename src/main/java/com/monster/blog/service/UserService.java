package com.monster.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.blog.common.api.R;
import com.monster.blog.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    Map<String, String> login(String username, String password);

    /**
     * 刷新token
     * @param request request
     * @return token
     */
    Map<String, String> refreshToken(HttpServletRequest request);

    /**
     * 获取用户名
     * @param username 用户信息
     * @return 用户
     */
    User getUsername(String username);

    /**
     * 生成验证码
     * @param telephone 手机号码
     * @return 验证码
     */
    String generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     * @param telephone 手机号码
     * @param authCode 验证码
     * @return result
     */
    R verifyAuthCode(String telephone, String authCode);
}
