package com.monster.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.entity.ArticleTag;
import com.monster.blog.mapper.ArticleTagMapper;
import com.monster.blog.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * @author wuhan
 * @date 2020/1/7 15:09
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {
}
