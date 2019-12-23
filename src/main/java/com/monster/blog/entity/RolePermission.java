package com.monster.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户角色-权限关系实体
 * @author Monster
 * @date 2019/12/23.
 */
@Data
@TableName("blog_role_permission")
@ApiModel(value = "RolePermission对象", description = "用户角色-权限关系实体")
public class RolePermission {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "权限id")
    private Long permissionId;
}
