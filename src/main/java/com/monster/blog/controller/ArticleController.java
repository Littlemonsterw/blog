package com.monster.blog.controller;

import com.monster.blog.common.api.R;
import com.monster.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiSort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @GetMapping("/likeCount")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "点赞", notes = "（增加/取消）点赞")
    public R<Boolean> likeCount(@RequestParam Long articleId) {
        return R.status(null);
    }
}
