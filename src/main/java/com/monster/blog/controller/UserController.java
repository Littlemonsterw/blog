package com.monster.blog.controller;

import com.monster.blog.common.api.R;
import com.monster.blog.entity.User;
import com.monster.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuhan
 * @date 2020/1/10 11:14
 */
@RestController
@RequestMapping("blog/user")
@Api(value = "用户基本信息", tags = "用户基本信息接口")
public class UserController {

    private UserService userService;

    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "用户详情,传入User")
    public R<User> detail(String username) {
        return null;
    }
}
