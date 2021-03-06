package com.monster.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.entity.ArticleCategory;
import com.monster.blog.mapper.ArticleCategoryMapper;
import com.monster.blog.service.ArticleCategoryService;
import org.springframework.stereotype.Service;

/**
 * @author wuhan
 * @date 2020/1/7 15:08
 */
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements ArticleCategoryService {

    @Override
    public Boolean updateCategory(Long articleId, Long categoryId) {
        return this.update(Wrappers.<ArticleCategory>lambdaUpdate().set(ArticleCategory::getCategoryId, categoryId).eq(ArticleCategory::getArticleId, articleId));
    }
}
