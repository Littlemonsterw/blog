package com.monster.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.entity.Tag;
import com.monster.blog.mapper.TagMapper;
import com.monster.blog.service.TagService;
import org.springframework.stereotype.Service;

/**
 * @author wuhan
 * @date 2020/1/7 15:11
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public Boolean addTag(Tag tag) {
        int count = baseMapper.selectCount(Wrappers.<Tag>lambdaQuery().eq(Tag::getName, tag.getName()));
        if (count > 0) {
            throw new ApiException("此标签已存在！");
        }
        return this.save(tag);
    }
}
