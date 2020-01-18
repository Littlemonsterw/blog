package com.monster.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.blog.entity.Comment;

/**
 * @author wuhan
 * @date 2020/1/7 15:09
 */
public interface CommentService extends IService<Comment> {

    /**
     * 添加评论
     * @param comment 评论信息
     * @return comment
     */
    Boolean addComment(Comment comment);

    /**
     * 给评论点赞/取消点赞
     * @param commentId 评论id
     * @return result
     */
    Boolean likeCount(Long commentId);
}
