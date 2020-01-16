package com.monster.blog.controller;

import com.github.pagehelper.PageHelper;
import com.monster.blog.common.api.IPage;
import com.monster.blog.common.api.R;
import com.monster.blog.entity.Tag;
import com.monster.blog.service.TagService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuhan
 * @date 2020/1/16 10:51
 */
@RestController
@RequestMapping("/blog/tag")
@ApiSort(value = 7)
@Api(value = "标签管理", tags = "标签管理接口")
public class TagController {

    @Resource
    private TagService tagService;

    @PostMapping("/add")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "新增", notes = "新增标签")
    public R<Boolean> add(Tag tag) {
        return R.status(tagService.addTag(tag));
    }

    @PutMapping("/update")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "修改", notes = "修改")
    public R<Boolean> update(Tag tag) {
        return R.status(tagService.updateById(tag));
    }

    @DeleteMapping("/remove")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除", notes = "批量删除标签")
    public R<Boolean> remove(List<Long> tagIds) {
        return R.status(tagService.removeByIds(tagIds));
    }

    @GetMapping("/list")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "获取标签列表", notes = "获取所有标签（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", dataType = "int", defaultValue = "5")
    })
    public IPage<Tag> list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return IPage.restPage(tagService.list());
    }
}
