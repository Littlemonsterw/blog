package com.monster.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.entity.Permission;
import com.monster.blog.entity.PermissionNode;
import com.monster.blog.mapper.PermissionMapper;
import com.monster.blog.service.PermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wuhan
 * @date 2020/1/7 15:10
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public Boolean addPermission(Permission permission) {
        int count = this.count(Wrappers.<Permission>lambdaQuery().eq(Permission::getName, permission.getName()));
        if (count > 0) {
            throw new ApiException("权限已存在！");
        }
        return this.save(permission);
    }

    @Override
    public List<PermissionNode> treeList() {
        List<Permission> permissionList = this.list();
        List<PermissionNode> result = permissionList.stream()
                .filter(permission -> permission.getParentId().equals(0L))
                .map(permission -> covert(permission, permissionList)).collect(Collectors.toList());
        return result;
    }

    /**
     * 将权限转换为带有子级的权限对象
     * 当找不到子级权限的时候map操作不会再递归调用covert
     *
     * @param permission 父级权限
     * @param permissionList 子级权限
     * @return result
     */
    private PermissionNode covert(Permission permission, List<Permission> permissionList) {
        PermissionNode node = new PermissionNode();
        BeanUtils.copyProperties(permission, node);
        List<PermissionNode> children = permissionList.stream()
                .filter(subPermission -> subPermission.getParentId().equals(permission.getId()))
                .map(subPermission -> covert(subPermission, permissionList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
