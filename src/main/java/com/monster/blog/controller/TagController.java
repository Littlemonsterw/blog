package com.monster.blog.controller;

import com.monster.blog.common.api.R;
import com.monster.blog.entity.Tag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiSort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuhan
 * @date 2020/1/16 10:51
 */
@RestController
@RequestMapping("/blog/tag")
@ApiSort(value = 7)
@Api(value = "标签管理", tags = "标签管理接口")
public class TagController {

    @PostMapping("/add")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "新增", notes = "新增标签")
    public R<Tag> add(Tag tag) {
        return R.status(null);
    }
}
