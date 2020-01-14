package com.monster.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.blog.entity.Role;

/**
 * @author wuhan
 * @date 2020/1/7 15:10
 */
public interface RoleService extends IService<Role> {

    /**
     * 添加角色
     * @param role 角色信息
     * @return result
     */
    Boolean addRole(Role role);
}
