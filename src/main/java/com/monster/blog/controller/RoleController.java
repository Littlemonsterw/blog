package com.monster.blog.controller;

import com.github.pagehelper.PageHelper;
import com.monster.blog.common.api.IPage;
import com.monster.blog.common.api.R;
import com.monster.blog.entity.Role;
import com.monster.blog.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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

    @PostMapping("/add")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "添加", notes = "添加角色信息")
    public R<Boolean> add(@Valid @RequestBody Role role) {
        return R.success(roleService.addRole(role), "角色创建成功！");
    }

    @PostMapping("/update")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "修改", notes = "修改角色信息")
    public R<Boolean> update(@Valid @RequestBody Role role) {
        return R.status(roleService.updateById(role), "角色修改成功！");
    }

    @PostMapping("/remove")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除", notes = "批量删除角色信息")
    public R<Boolean> remove(@RequestParam List<Long> ids) {
        return R.success(roleService.removeByIds(ids), "角色删除删除成功！");
    }

    @GetMapping("/list")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "获取角色列表", notes = "获取所有角色")
    public IPage<Role> list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return IPage.restPage(roleService.list());
    }
}
