package com.monster.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文章标签实体
 * @author Monster
 * @date 2019/12/23.
 */
@Data
@TableName("blog_tag")
@ApiModel(value = "Tag对象", description = "文章标签实体")
public class Tag {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;
}
