package com.monster.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.entity.Category;
import com.monster.blog.mapper.CategoryMapper;
import com.monster.blog.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @author wuhan
 * @date 2020/1/7 15:09
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public Boolean addCategory(Category category) {
        int count = baseMapper.selectCount(Wrappers.<Category>lambdaQuery().eq(Category::getName, category.getName()));
        if (count > 0) {
            throw new ApiException("此分类已存在！");
        }
        return this.save(category);
    }
}
