package com.monster.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.blog.entity.Tag;

/**
 * @author wuhan
 * @date 2020/1/7 15:11
 */
public interface TagService extends IService<Tag> {

    /**
     * 添加标签
     * @param tag 标签信息
     * @return tag
     */
    Boolean addTag(Tag tag);
}
