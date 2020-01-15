package com.monster.blog.controller;

import com.github.pagehelper.PageHelper;
import com.monster.blog.common.api.IPage;
import com.monster.blog.common.api.R;
import com.monster.blog.entity.Permission;
import com.monster.blog.entity.PermissionNode;
import com.monster.blog.service.PermissionService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Monster
 * @date 2020/1/13.
 */
@RestController
@RequestMapping("/blog/permission")
@Api(value = "权限管理", tags = "权限管理接口")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @PostMapping("/add")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "添加", notes = "添加权限")
    public R<Boolean> add(Permission permission) {
        return R.status(permissionService.addPermission(permission));
    }

    @PostMapping("/update")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "修改", notes = "修改权限信息")
    public R<Boolean> update(Permission permission) {
        return R.status(permissionService.updateById(permission));
    }

    @PostMapping("/remove")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除", notes = "批量删除权限")
    @ApiImplicitParam(name = "permissionIds", value = "权限ids", required = true)
    public R<Boolean> remove(@RequestParam List<Long> ids) {
        return R.success(permissionService.removeByIds(ids), "权限删除成功！");
    }

    @GetMapping("/list")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "获取权限列表", notes = "获取所有权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", dataType = "int", defaultValue = "5")
    })
    public IPage<Permission> list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return IPage.restPage(permissionService.list());
    }

    @GetMapping("/treeList")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "获取权限树形结构", notes = "以层级结构返回所有权限")
    public R<List<PermissionNode>> treeList() {
        return R.data(permissionService.treeList());
    }
}
