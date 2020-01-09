package com.monster.blog.controller;

import com.monster.blog.common.api.R;
import com.monster.blog.entity.User;
import com.monster.blog.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Monster
 * @date 2019/12/26.
 */
@RestController
@RequestMapping("blog/")
@Api(value = "用户授权认证", tags = "用户登录注册管理")
public class AuthController {

    @Autowired
    private UserService userService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

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
        tokenMap.put("tokenHeader", tokenHeader);
        tokenMap.put("tokenHead", tokenHead);
        return R.data(tokenMap);
    }

    @GetMapping("/getAuthCode")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "获取验证码", notes = "获取注册验证码")
    public R getAuthCode(@RequestParam String telephone) {
        return R.data(userService.generateAuthCode(telephone));
    }

    @PostMapping("/verifyAuthCode")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "判断验证码是否正确", notes = "检查验证码")
    public R verifyAuthCode(@RequestParam String telephone, @RequestParam String authCode) {
        return R.data(userService.verifyAuthCode(telephone, authCode));
    }
}
