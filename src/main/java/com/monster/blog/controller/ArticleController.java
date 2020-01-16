package com.monster.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Monster
 * @date 2020/1/13.
 */
@RestController
@RequestMapping("/blog/article")
@ApiSort(value = 5)
@Api(value = "文章管理", tags = "文章管理接口")
public class ArticleController {
}
