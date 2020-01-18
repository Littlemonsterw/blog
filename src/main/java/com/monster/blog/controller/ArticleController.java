package com.monster.blog.controller;

import com.github.pagehelper.PageHelper;
import com.monster.blog.common.api.IPage;
import com.monster.blog.common.api.R;
import com.monster.blog.entity.Article;
import com.monster.blog.service.ArticleCategoryService;
import com.monster.blog.service.ArticleService;
import com.monster.blog.service.ArticleTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiSort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Monster
 * @date 2020/1/13.
 */
@RestController
@RequestMapping("/blog/article")
@ApiSort(value = 5)
@Api(value = "文章管理", tags = "文章管理接口")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleTagService articleTagService;

    @Resource
    private ArticleCategoryService articleCategoryService;

    @PostMapping("/add")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "新增", notes = "发布文章")
    public R<Boolean> add(Article article) {
        return R.status(null);
    }

    @PutMapping("/update")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "修改", notes = "修改文章")
    public R<Boolean> update(Article article) {
        return R.status(articleService.updateById(article));
    }

    @PutMapping("/updateTag")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "修改标签", notes = "修改文章所属标签")
    public R<Boolean> updateTag(@RequestParam Long articleId, @RequestParam List<Long> tagIds) {
        return R.status(articleTagService.updateTag(articleId, tagIds));
    }

    @PutMapping("/updateCategory")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "修改分类", notes = "修改文章所属分类")
    public R<Boolean> updateCategory(@RequestParam Long articleId, @RequestParam Long categoryId) {
        return R.status(articleCategoryService.updateCategory(articleId, categoryId));
    }

    @DeleteMapping("/remove")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除", notes = "批量删除文章")
    public R<Boolean> remove(@RequestParam List<Long> articleIds) {
        return R.status(articleService.removeArticle(articleIds));
    }

    @GetMapping("/list")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "获取文章列表", notes = "分页获取当前用户所有文章")
    public IPage<Article> list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return IPage.restPage(articleService.list());
    }

    @GetMapping("/likeCount")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "点赞", notes = "（增加/取消）点赞")
    public R<Boolean> likeCount(@RequestParam Long articleId) {
        return R.status(articleService.likeCount(articleId));
    }
}
