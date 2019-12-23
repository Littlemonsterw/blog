package com.monster.blog.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 博客文章实体
 * @author Monster
 * @date 2019/12/23.
 */
@Data
@TableName("blog_article")
@ApiModel(value = "Article对象", description = "博客文章实体")
public class Article {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "发表用户id")
    private Long userId;

    @ApiModelProperty(value = "文章分类id")
    private Long categoryId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "内容摘要")
    private String summary;

    @ApiModelProperty(value = "浏览量")
    private Integer pageView;

    @ApiModelProperty(value = "点赞数")
    private Integer likeCount;

    @ApiModelProperty(value = "评论总数")
    private Integer commentCount;

    @ApiModelProperty(value = "创建时间")
    private DateTime createTime;

    @ApiModelProperty(value = "发布时间")
    private DateTime publishTime;

    @ApiModelProperty(value = "更新时间")
    private DateTime updateTime;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "0表示草稿箱，1表示已发表，2表示已删除")
    private Integer state;
}
