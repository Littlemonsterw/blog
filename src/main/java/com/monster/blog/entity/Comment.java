package com.monster.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 评论信息实体
 * @author Monster
 * @date 2019/12/23.
 */
@Data
@TableName("blog_comment")
@ApiModel(value = "Comment对象", description = "评论信息实体")
public class Comment {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "发表用户")
    private String publishUsername;

    @ApiModelProperty(value = "文章id")
    private Long articleId;

    @ApiModelProperty(value = "父评论id")
    private Long parentId;

    @ApiModelProperty(value = "点赞数")
    private Integer likeCount;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论时间", hidden = true)
    private Date commentTime;
}
