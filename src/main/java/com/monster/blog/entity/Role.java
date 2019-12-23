package com.monster.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户角色实体
 * @author Monster
 * @date 2019/12/23.
 */
@Data
@TableName("blog_role")
@ApiModel(value = "Role对象", description = "用户角色实体")
public class Role {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "用户数量")
    private Integer count;

    @ApiModelProperty(value = "启用状态: 0->禁用, 1->启用")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}
