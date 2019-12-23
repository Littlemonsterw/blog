package com.monster.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户权限实体
 * @author Monster
 * @date 2019/12/23.
 */
@Data
@TableName("blog_permission")
@ApiModel(value = "Permission对象", description = "用户权限实体")
public class Permission {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "父级权限id")
    private Long parentId;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限值")
    private String value;

    @ApiModelProperty(value = "权限类型：0->目录, 1->菜单, 2->按钮（接口绑定权限)")
    private Integer type;

    @ApiModelProperty(value = "启用状态: 0->禁用, 1->启用")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}
