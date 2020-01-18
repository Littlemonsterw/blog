package com.monster.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.blog.entity.ArticleCategory;

/**
 * @author wuhan
 * @date 2020/1/7 15:08
 */
public interface ArticleCategoryService extends IService<ArticleCategory> {

    /**
     * 修改文章分类
     * @param articleId 文章id
     * @param categoryId 分类id
     * @return result
     */
    Boolean updateCategory(Long articleId, Long categoryId);
}
