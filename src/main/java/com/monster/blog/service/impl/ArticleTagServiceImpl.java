package com.monster.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.entity.ArticleTag;
import com.monster.blog.mapper.ArticleTagMapper;
import com.monster.blog.service.ArticleTagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuhan
 * @date 2020/1/7 15:09
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    @Override
    public Boolean updateTag(Long articleId, List<Long> tagIds) {
        int count = baseMapper.selectCount(Wrappers.<ArticleTag>lambdaQuery().eq(ArticleTag::getArticleId, articleId));
        if (count > 0) {
            baseMapper.delete(Wrappers.<ArticleTag>lambdaQuery().eq(ArticleTag::getArticleId, articleId));
            List<ArticleTag> list = new ArrayList<>();
            for (Long tagId: tagIds) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(articleId);
                articleTag.setTagId(tagId);
                list.add(articleTag);
            }
            this.saveBatch(list);
        }
        return true;
    }
}
