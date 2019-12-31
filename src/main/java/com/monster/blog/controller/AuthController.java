package com.monster.blog.controller;

import com.monster.blog.common.api.R;
import com.monster.blog.entity.User;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Monster
 * @date 2019/12/26.
 */
@RestController
@AllArgsConstructor
@RequestMapping("blog/")
@Api(value = "用户授权认证", tags = "授权接口")
public class AuthController {

    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "传入注册信息")
    public R register(User user) {
        return null;
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆", notes = "登陆验证，登陆成功返回token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true)
    })
    public R login(@RequestParam String username, @RequestParam String password) {
        return null;
    }
}
