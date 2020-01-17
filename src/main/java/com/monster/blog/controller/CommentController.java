package com.monster.blog.controller;

import com.monster.blog.common.api.R;
import com.monster.blog.entity.Comment;
import com.monster.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiSort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuhan
 * @date 2020/1/16 10:55
 */
@RestController
@RequestMapping("/blog/comment")
@ApiSort(value = 6)
@Api(value = "评论管理", tags = "评论管理接口")
public class CommentController {

    @Resource
    private CommentService commentService;

    @PostMapping("/add")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "新增", notes = "新增评论")
    public R<Boolean> add(Comment comment) {
        return R.status(commentService.addComment(comment));
    }

    @DeleteMapping("/remove")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "删除", notes = "批量删除评论")
    public R<Boolean> remove(@RequestParam List<Long> ids) {
        return R.status(commentService.removeByIds(ids));
    }
}
