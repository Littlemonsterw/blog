package com.monster.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monster.blog.entity.Category;

/**
 * @author wuhan
 * @date 2020/1/7 15:09
 */
public interface CategoryService extends IService<Category> {

    /**
     * 新增分类
     * @param category 分类信息
     * @return category
     */
    Boolean addCategory(Category category);
}
