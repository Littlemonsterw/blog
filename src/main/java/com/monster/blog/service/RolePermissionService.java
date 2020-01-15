package com.monster.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.blog.entity.Permission;
import com.monster.blog.entity.RolePermission;

import java.util.List;

/**
 * @author wuhan
 * @date 2020/1/7 15:11
 */
public interface RolePermissionService extends IService<RolePermission> {

    /**
     * 给角色分配权限
     * @param roleId 角色id
     * @param permissionIds 权限id
     * @return result
     */
    Boolean grantPermission(Long roleId, List<Long> permissionIds);

    /**
     * 获取指定用户的权限信息
     * @param roleId 角色id
     * @return 角色的所有权限
     */
    List<Permission> getRolePermission(Long roleId);
}
