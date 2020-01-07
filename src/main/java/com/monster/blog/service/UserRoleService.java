package com.monster.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.blog.entity.Permission;
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
}
