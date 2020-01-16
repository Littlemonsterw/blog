package com.monster.blog.controller;

import com.github.pagehelper.PageHelper;
import com.monster.blog.common.api.IPage;
import com.monster.blog.common.api.R;
import com.monster.blog.entity.Category;
import com.monster.blog.service.CategoryService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuhan
 * @date 2020/1/16 10:54
 */
@RestController
@RequestMapping("/blog/category")
@ApiSort(value = 8)
@Api(value = "分类管理", tags = "分类管理接口")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/add")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "新增", notes = "新增分类")
    public R<Boolean> add(Category category) {
        return R.status(categoryService.addCategory(category));
    }

    @PutMapping("/update")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "修改", notes = "修改分类信息")
    public R<Boolean> update(Category category) {
        return R.status((categoryService.updateById(category)));
    }

    @DeleteMapping("/remove")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除", notes = "批量删除")
    public R<Boolean> remove(@RequestParam List<Long> ids) {
        return R.status(categoryService.removeByIds(ids));
    }

    @GetMapping("/list")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "获取分类列表", notes = "获取所有分类信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", dataType = "int", defaultValue = "5")
    })
    public IPage<Category> list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return IPage.restPage(categoryService.list());
    }
}
