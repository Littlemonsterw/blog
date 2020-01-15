package com.monster.blog.controller;

import com.github.pagehelper.PageHelper;
import com.monster.blog.common.api.IPage;
import com.monster.blog.common.api.R;
import com.monster.blog.entity.Permission;
import com.monster.blog.entity.Role;
import com.monster.blog.service.RolePermissionService;
import com.monster.blog.service.RoleService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Monster
 * @date 2020/1/13.
 */
@RestController
@RequestMapping("/blog/role")
@Api(value = "角色管理", tags = "用户角色管理接口")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private RolePermissionService rolePermissionService;

    @PostMapping("/add")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "添加", notes = "添加角色信息")
    public R<Boolean> add(Role role) {
        return R.success(roleService.addRole(role), "角色创建成功！");
    }

    @PostMapping("/update")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "修改", notes = "修改角色信息")
    public R<Boolean> update(Role role) {
        return R.status(roleService.updateById(role));
    }

    @PostMapping("/remove")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除", notes = "批量删除角色信息")
    @ApiImplicitParam(name = "ids", value = "角色ids", required = true)
    public R<Boolean> remove(@RequestParam List<Long> ids) {
        return R.success(roleService.removeByIds(ids), "角色删除成功！");
    }

    @GetMapping("/list")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "获取角色列表", notes = "获取所有角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", dataType = "int", defaultValue = "5")
    })
    public IPage<Role> list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return IPage.restPage(roleService.list());
    }

    @GetMapping("/getPermission")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "获取角色的权限", notes = "获取指定角色的所有权限")
    @ApiImplicitParam(name = "roleId", value = "角色id", required = true)
    public R<List<Permission>> getRolePermission(@RequestParam Long roleId) {
        return R.data(rolePermissionService.getRolePermission(roleId));
    }

    @PostMapping("/updatePermission")
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "修改角色权限", notes = "修改角色权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true),
            @ApiImplicitParam(name = "permissionIds", value = "权限ids", required = true)
    })
    public R<Boolean> updatePermission(@RequestParam Long roleId, @RequestParam List<Long> permissionIds) {
        return R.status(null);
    }
}
