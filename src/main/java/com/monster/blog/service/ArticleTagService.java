package com.monster.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.blog.entity.ArticleTag;

import java.util.List;

/**
 * @author wuhan
 * @date 2020/1/7 15:08
 */
public interface ArticleTagService extends IService<ArticleTag> {

    /**
     * 修改文章标签
     * @param articleId 文章id
     * @param tagIds 标签id
     * @return result
     */
    Boolean updateTag(Long articleId, List<Long> tagIds);
}
