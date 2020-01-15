package com.monster.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.entity.Permission;
import com.monster.blog.entity.RolePermission;
import com.monster.blog.mapper.RolePermissionMapper;
import com.monster.blog.service.RolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuhan
 * @date 2020/1/7 15:11
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean grantPermission(Long roleId, List<Long> permissionIds) {
        List<RolePermission> list = this.list(Wrappers.<RolePermission>lambdaQuery().eq(RolePermission::getRoleId, roleId));
        if (CollectionUtils.isNotEmpty(list)) {
            baseMapper.delete(Wrappers.<RolePermission>lambdaQuery().eq(RolePermission::getRoleId, roleId));
        }

        List<RolePermission> rolePermissionList = new ArrayList<>();
        for (Long permissionId: permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionList.add(rolePermission);
        }

        return this.saveBatch(rolePermissionList);
    }

    @Override
    public List<Permission> getRolePermission(Long roleId) {
        return baseMapper.getRolePermission(roleId);
    }
}
