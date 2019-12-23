package com.monster.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文章分类表
 * @author Monster
 * @date 2019/12/23.
 */
@Data
@TableName("blog_category")
@ApiModel(value = "category对象", description = "文章分类实体")
public class Category {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;
}
