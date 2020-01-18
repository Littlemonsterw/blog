package com.monster.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.common.utils.SecureUtil;
import com.monster.blog.entity.Article;
import com.monster.blog.entity.LikeCount;
import com.monster.blog.mapper.ArticleMapper;
import com.monster.blog.service.ArticleService;
import com.monster.blog.service.LikeCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author wuhan
 * @date 2020/1/7 15:07
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private LikeCountService likeCountService;

    @Override
    public Boolean likeCount(Long articleId) {
        Article article = baseMapper.selectById(articleId);
        int count = likeCountService.count(Wrappers.<LikeCount>lambdaQuery().eq(LikeCount::getArticleId, articleId).eq(LikeCount::getUsername, SecureUtil.getCurrentUser()));
        if (count > 0) {
            baseMapper.update(article, Wrappers.<Article>lambdaUpdate().set(Article::getLikeCount, article.getLikeCount() - 1).eq(Article::getId, articleId));
            likeCountService.remove(Wrappers.<LikeCount>lambdaQuery().eq(LikeCount::getArticleId, articleId));
        } else {
            baseMapper.update(article, Wrappers.<Article>lambdaUpdate().set(Article::getLikeCount, article.getLikeCount() + 1).eq(Article::getId, articleId));
            LikeCount likeCount = new LikeCount();
            likeCount.setArticleId(articleId);
            likeCount.setUsername(SecureUtil.getCurrentUser());
            likeCount.setCreateTime(new Date());
            likeCountService.save(likeCount);
        }
        return true;
    }
}
