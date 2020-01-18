package com.monster.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.entity.Role;
import com.monster.blog.entity.RolePermission;
import com.monster.blog.mapper.RoleMapper;
import com.monster.blog.service.RolePermissionService;
import com.monster.blog.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuhan
 * @date 2020/1/7 15:10
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RolePermissionService rolePermissionService;

    @Override
    public Boolean addRole(Role role) {
        int count = baseMapper.selectCount(Wrappers.<Role>lambdaQuery().eq(Role::getName, role.getName()));
        if (count > 0) {
            throw new ApiException("角色已存在！");
        }
        return this.save(role);
    }

    @Override
    public Boolean removeRole(List<Long> roleIds) {
        int count = rolePermissionService.count(Wrappers.<RolePermission>lambdaQuery().in(RolePermission::getRoleId, roleIds));
        if (count > 0) {
            rolePermissionService.remove(Wrappers.<RolePermission>lambdaQuery().in(RolePermission::getRoleId, roleIds));
        }
        return this.removeByIds(roleIds);
    }
}
