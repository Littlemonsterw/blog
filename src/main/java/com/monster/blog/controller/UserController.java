package com.monster.blog.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.monster.blog.common.api.IPage;
import com.monster.blog.common.api.R;
import com.monster.blog.entity.User;
import com.monster.blog.service.UserService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuhan
 * @date 2020/1/10 11:14
 */
@RestController
@RequestMapping("blog/user")
@Api(value = "用户基本信息", tags = "用户基本信息接口")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "用户详情,传入User")
    public R<User> detail(String username) {
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        return R.data(user);
    }

    @GetMapping("/userInfoPage")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页查询用户信息列表", notes = "用户信息列表（分页）")
    public IPage<User> userInfoPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return IPage.restPage(userService.list());
    }
}
