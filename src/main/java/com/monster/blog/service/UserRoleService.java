package com.monster.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.blog.entity.Permission;
import com.monster.blog.entity.Role;
import com.monster.blog.entity.UserRole;

import java.util.List;

/**
 * @author wuhan
 * @date 2020/1/7 15:13
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 获取用户权限列表
     * @param userId 用户id
     * @return 权限列表
     */
    List<Permission> getPermissionList(Long userId);

    /**
     * 给用户分配角色
     * @param userId 用户id
     * @param roleIds 角色id集合
     * @return result
     */
    Boolean grantRole(Long userId, List<Long> roleIds);

    /**
     * 获取指定用户的角色信息
     * @param userId 用户id
     * @return 用户所属角色
     */
    List<Role> getUserRole(Long userId);
}
