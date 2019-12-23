package com.monster.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户-角色关系实体
 * @author Monster
 * @date 2019/12/23.
 */
@Data
@TableName("blog_user_role")
@ApiModel(value = "UserRole对象", description = "用户-角色关系实体")
public class UserRole {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "角色id")
    private Long roleId;
}
