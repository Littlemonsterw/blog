package com.monster.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.entity.Comment;
import com.monster.blog.mapper.CommentMapper;
import com.monster.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wuhan
 * @date 2020/1/7 15:09
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public Boolean addComment(Comment comment) {
        comment.setCommentTime(new Date());
        return null;
    }
}
