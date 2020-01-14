package com.monster.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.entity.Role;
import com.monster.blog.mapper.RoleMapper;
import com.monster.blog.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author wuhan
 * @date 2020/1/7 15:10
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public Boolean addRole(Role role) {
        int count = this.count(Wrappers.<Role>lambdaQuery().eq(Role::getName, role.getName()));
        if (count > 0) {
            throw new ApiException("角色已存在！");
        }
        return this.save(role);
    }
}
