package com.monster.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.monster.blog.entity.Permission;
import com.monster.blog.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuhan
 * @date 2019/12/31 15:41
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 获取用户权限
     * @param userId 用户id
     * @return 用户权限
     */
    List<Permission> getPermissionList(@Param("userId") Long userId);
}
