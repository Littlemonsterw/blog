package com.monster.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuhan
 * @date 2020/1/16 10:55
 */
@RestController
@RequestMapping("/blog/comment")
@ApiSort(value = 6)
@Api(value = "评论管理", tags = "评论管理接口")
public class CommentController {
}
