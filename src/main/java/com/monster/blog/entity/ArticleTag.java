package com.monster.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文章标签关联实体
 * @author Monster
 * @date 2019/12/23.
 */
@Data
@TableName("blog_article_tag")
@ApiModel(value = "ArticleTag对象", description = "文章-标签关联关系实体")
public class ArticleTag {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "文章id")
    private Long articleId;

    @ApiModelProperty(value = "标签id")
    private Long tagId;
}
