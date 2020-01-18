package com.monster.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.blog.entity.Article;

import java.util.List;

/**
 * @author wuhan
 * @date 2020/1/7 15:07
 */
public interface ArticleService extends IService<Article> {

    /**
     * 删除文章
     * @param articleIds 文章id
     * @return result
     */
    Boolean removeArticle(List<Long> articleIds);

    /**
     * 给评论点赞/取消点赞
     * @param articleId 评论id
     * @return result
     */
    Boolean likeCount(Long articleId);
}
