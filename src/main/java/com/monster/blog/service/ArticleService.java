package com.monster.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.blog.entity.Article;

/**
 * @author wuhan
 * @date 2020/1/7 15:07
 */
public interface ArticleService extends IService<Article> {

    /**
     * 给评论点赞/取消点赞
     * @param articleId 评论id
     * @return result
     */
    Boolean likeCount(Long articleId);
}
