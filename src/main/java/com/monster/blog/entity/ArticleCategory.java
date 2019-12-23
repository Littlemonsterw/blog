package com.monster.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文章-分类关联实体
 * @author Monster
 * @date 2019/12/23.
 */
@Data
@TableName("blog_article_category")
@ApiModel(value = "ArticleCategory", description = "章-分类关联关系实体")
public class ArticleCategory {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "文章id")
    private Long articleId;

    @ApiModelProperty(value = "分类id")
    private Long categoryId;
}
