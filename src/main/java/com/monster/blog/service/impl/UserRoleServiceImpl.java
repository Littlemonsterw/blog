package com.monster.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.entity.Permission;
import com.monster.blog.entity.Role;
import com.monster.blog.entity.UserRole;
import com.monster.blog.mapper.UserRoleMapper;
import com.monster.blog.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean grantRole(Long userId, List<Long> roleIds) {
        List<UserRole> list = this.list(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));
        if (CollectionUtils.isNotEmpty(list)) {
            baseMapper.delete(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));
        }

        List<UserRole> userRoleList = new ArrayList<>();
        for (Long roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }

        return this.saveBatch(userRoleList);
    }

    @Override
    public List<Role> getUserRole(Long userId) {
        return baseMapper.getUserRole(userId);
    }
}
