package com.monster.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.monster.blog.entity.Permission;
import com.monster.blog.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuhan
 * @date 2019/12/31 15:53
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 获取指定用户的权限信息
     * @param roleId 角色id
     * @return 角色的所有权限
     */
    List<Permission> getRolePermission(@Param("roleId") Long roleId);
}
