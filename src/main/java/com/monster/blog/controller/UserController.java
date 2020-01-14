package com.monster.blog.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.monster.blog.common.api.IPage;
import com.monster.blog.common.api.R;
import com.monster.blog.entity.User;
import com.monster.blog.service.UserService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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
    @ApiOperation(value = "获取指定用户信息", notes = "根据用户名获取指定用户信息")
    @ApiImplicitParam(name = "username", value = "用户名", required = true)
    public R<User> detail(String username) {
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        return R.data(user);
    }

    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页查询用户信息列表", notes = "获取用户信息列表（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", dataType = "int")
    })
    public IPage<User> list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return IPage.restPage(userService.list());
    }

    @PostMapping("/update")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "修改", notes = "修改用户信息")
    public R<Boolean> update(@Valid @RequestBody User user) {
        return R.data(userService.updateById(user));
    }

    @PostMapping("/remove")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "删除", notes = "批量删除用户")
    public R<Boolean> remove(@RequestParam List<Long> ids) {
        return R.data(userService.removeByIds(ids));
    }

    @PostMapping("/bindRole")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "绑定角色", notes = "给用户分配角色")
    public R<Boolean> bindRole(@RequestParam Long userId, @RequestParam List<Long> roleIds) {
        return null;
    }
}
