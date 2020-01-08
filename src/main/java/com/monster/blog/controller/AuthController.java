package com.monster.blog.controller;

import com.monster.blog.common.api.R;
import com.monster.blog.entity.User;
import com.monster.blog.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Monster
 * @date 2019/12/26.
 */
@RestController
@RequestMapping("blog/")
@Api(value = "用户授权认证", tags = "授权接口")
public class AuthController {

    @Autowired
    private UserService userService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @PostMapping("/register")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "用户注册", notes = "传入注册信息")
    public R<User> register(User user) {
        return R.data(userService.register(user));
    }

    @PostMapping("/login")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "用户登陆", notes = "登录验证，登陆成功返回token")
    public R login(@RequestParam String username, @RequestParam String password) {
        String token = userService.login(username, password);
        if (Objects.isNull(token)) {
            return R.validateFailed("用户名或密码错误！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return R.data(tokenMap);
    }
}
