package com.monster.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author wuhan
 * @date 2020/1/18 10:53
 */
@Data
@TableName("blog_like_count")
@ApiModel(value = "LikeCount对象", description = "点赞记录表")
public class LikeCount {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "文章id")
    private Long articleId;

    @ApiModelProperty(value = "评论id")
    private Long commentId;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;
}
