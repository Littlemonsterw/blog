package com.monster.blog.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.monster.blog.common.api.IPage;
import com.monster.blog.common.api.R;
import com.monster.blog.entity.Permission;
import com.monster.blog.entity.Role;
import com.monster.blog.entity.User;
import com.monster.blog.service.RolePermissionService;
import com.monster.blog.service.UserRoleService;
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

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RolePermissionService rolePermissionService;

    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取用户信息", notes = "根据用户名获取指定用户信息")
    @ApiImplicitParam(name = "username", value = "用户名", required = true)
    public R<User> detail(String username) {
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        return R.data(user);
    }

    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页查询用户信息列表", notes = "获取用户信息列表（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", dataType = "int", defaultValue = "5")
    })
    public IPage<User> list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return IPage.restPage(userService.list());
    }

    @PostMapping("/update")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "修改", notes = "修改用户信息")
    public R<Boolean> update(@Valid User user) {
        return R.status(userService.updateById(user));
    }

    @PostMapping("/remove")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "删除", notes = "批量删除用户")
    @ApiImplicitParam(name = "ids", value = "用户ids", required = true)
    public R<Boolean> remove(@RequestParam List<Long> ids) {
        return R.status(userService.removeByIds(ids));
    }

    @PostMapping("/grantRole")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "分配角色", notes = "给用户分配角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true),
            @ApiImplicitParam(name = "roleIds", value = "角色ids", required = true)
    })
    public R<Boolean> grantRole(@RequestParam Long userId, @RequestParam List<Long> roleIds) {
        return R.status(userRoleService.grantRole(userId, roleIds));
    }

    @GetMapping("/getUserRole")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "获取用户的角色", notes = "获取指定用户的角色信息")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    public R<List<Role>> getUserRole(@RequestParam Long userId) {
        return R.data(userRoleService.getUserRole(userId));
    }

    @PostMapping("/grantPermission")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "分配权限", notes = "给角色分配权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true),
            @ApiImplicitParam(name = "permissionIds", value = "权限ids", required = true)
    })
    public R<Boolean> grantPermission(@RequestParam Long roleId, @RequestParam List<Long> permissionIds) {
        return R.status(rolePermissionService.grantPermission(roleId, permissionIds));
    }

    @GetMapping("/getUserPermission")
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "获取用户权限列表", notes = "获取用户权限列表")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    public R<List<Permission>> getUserPermission(@RequestParam Long userId) {
        return R.data(userRoleService.getPermissionList(userId));
    }
}
