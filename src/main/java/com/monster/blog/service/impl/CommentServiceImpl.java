package com.monster.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.common.utils.SecureUtil;
import com.monster.blog.entity.Comment;
import com.monster.blog.mapper.CommentMapper;
import com.monster.blog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wuhan
 * @date 2020/1/7 15:09
 */
@Service
@Slf4j
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public Boolean addComment(Comment comment) {
        if (StrUtil.isEmpty(comment.getContent())) {
            throw new ApiException("评论内容不能为空！");
        }
        comment.setCommentTime(new Date());
        comment.setPublishUsername(SecureUtil.getCurrentUser());
        return this.save(comment);
    }
}
