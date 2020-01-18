package com.monster.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.common.utils.SecureUtil;
import com.monster.blog.entity.Comment;
import com.monster.blog.entity.LikeCount;
import com.monster.blog.mapper.CommentMapper;
import com.monster.blog.service.CommentService;
import com.monster.blog.service.LikeCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author wuhan
 * @date 2020/1/7 15:09
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private LikeCountService likeCountService;

    @Override
    public Boolean addComment(Comment comment) {
        if (StrUtil.isEmpty(comment.getContent())) {
            throw new ApiException("评论内容不能为空！");
        }
        comment.setCommentTime(new Date());
        comment.setPublishUsername(SecureUtil.getCurrentUser());
        return this.save(comment);
    }

    @Override
    public Boolean likeCount(Long commentId) {
        Comment comment = baseMapper.selectById(commentId);
        int count = likeCountService.count(Wrappers.<LikeCount>lambdaQuery().eq(LikeCount::getUsername, SecureUtil.getCurrentUser()).eq(LikeCount::getCommentId, commentId));
        if (count > 0) {
            baseMapper.update(comment, Wrappers.<Comment>lambdaUpdate().set(Comment::getLikeCount, comment.getLikeCount() - 1).eq(Comment::getId, commentId));
            likeCountService.remove(Wrappers.<LikeCount>lambdaQuery().eq(LikeCount::getCommentId, commentId));
        } else {
            baseMapper.update(comment, Wrappers.<Comment>lambdaUpdate().set(Comment::getLikeCount, comment.getLikeCount() + 1).eq(Comment::getId, commentId));
            LikeCount likeCount = new LikeCount();
            likeCount.setCommentId(commentId);
            likeCount.setUsername(SecureUtil.getCurrentUser());
            likeCount.setCreateTime(new Date());
            likeCountService.save(likeCount);
        }
        return true;
    }
}
