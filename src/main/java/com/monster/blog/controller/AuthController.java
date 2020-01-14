package com.monster.blog.controller;

import com.monster.blog.common.api.R;
import com.monster.blog.entity.User;
import com.monster.blog.service.UserService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author Monster
 * @date 2019/12/26.
 */
@RestController
@RequestMapping("blog/auth")
@Api(value = "用户授权认证", tags = "用户登录注册管理接口")
public class AuthController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "用户注册", notes = "传入注册信息")
    public R<User> register(@Valid @RequestBody User user) {
        return R.data(userService.register(user));
    }

    @PostMapping("/login")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "用户登录", notes = "登录验证，登录成功返回token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    public R<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {
        return R.data(userService.login(username, password));
    }

    @GetMapping("/refreshToken")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "刷新token", notes = "刷新token")
    public R<Map<String, String>> refreshToken(HttpServletRequest request) {
        return R.success(userService.refreshToken(request));
    }

    @GetMapping("/getAuthCode")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "获取验证码", notes = "获取注册验证码")
    public R<String> getAuthCode(@ApiParam(value = "手机号", required = true) @RequestParam String telephone) {
        return R.data(userService.generateAuthCode(telephone));
    }

    @PostMapping("/verifyAuthCode")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "判断验证码是否正确", notes = "检查验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "authCode", value = "验证码", required = true, dataType = "String")
    })
    public R<R<String>> verifyAuthCode(@RequestParam String telephone, @RequestParam String authCode) {
        return R.data(userService.verifyAuthCode(telephone, authCode));
    }
}
