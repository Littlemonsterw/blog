package com.monster.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.blog.entity.Permission;
import com.monster.blog.entity.PermissionNode;

import java.util.List;

/**
 * @author wuhan
 * @date 2020/1/7 15:10
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 添加权限
     * @param permission 权限信息
     * @return result
     */
    Boolean addPermission(Permission permission);

    /**
     * 树形结构
     * @return list
     */
    List<PermissionNode> treeList();
}
