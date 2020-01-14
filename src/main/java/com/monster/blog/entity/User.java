package com.monster.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.Date;

/**
 * 用户实体
 * @author Monster
 * @date 2019/12/23.
 */
@Data
@TableName("blog_user")
@ApiModel(value = "User对象", description = "用户实体")
public class User {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "邮箱")
    @Email
    private String email;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间", dataType = "Date", hidden = true)
    private Date createTime;

    @ApiModelProperty(value = "最后登录时间", dataType = "Date", hidden = true)
    private Date loginLastTime;

    @ApiModelProperty(value = "启用状态: 0->禁用, 1->启用")
    private Integer status;
}
