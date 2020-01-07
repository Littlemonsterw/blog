package com.monster.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.entity.Permission;
import com.monster.blog.entity.UserRole;
import com.monster.blog.mapper.UserRoleMapper;
import com.monster.blog.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuhan
 * @date 2020/1/7 15:13
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Override
    public List<Permission> getPermissionList(Long userId) {
        return baseMapper.getPermissionList(userId);
    }
}
