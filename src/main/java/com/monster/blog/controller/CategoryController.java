package com.monster.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuhan
 * @date 2020/1/16 10:54
 */
@RestController
@RequestMapping("/blog/category")
@ApiSort(value = 8)
@Api(value = "分类管理", tags = "分类管理接口")
public class CategoryController {
}
