package com.monster.blog.common.api;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *分页数据封装类
 * @author wuhan
 * @date 2020/1/13 10:48
 */
@Data
public class IPage<T> {

    @ApiModelProperty(value = "当前页")
    private Integer pageNum;

    @ApiModelProperty(value = "每页显示数量")
    private Integer pageSize;

    @ApiModelProperty(value = "总页数")
    private Integer totalPage;

    @ApiModelProperty(value = "总记录数")
    private Long total;

    @ApiModelProperty(value = "结果集")
    private List<T> list;

    /**
     * 将PageHelper分页后的list转为分页信息
     * @param list 结果集
     * @param <T> 类型
     * @return 分页结果
     */
    public static <T> IPage<T> restPage(List<T> list) {
        IPage<T> result = new IPage<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotalPage(pageInfo.getPages());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }

    public static <T> IPage<T> restPage(Page<T> pageInfo) {
        IPage<T> result = new IPage<>();
        result.setPageNum(pageInfo.getNumber());
        result.setPageSize(pageInfo.getSize());
        result.setTotalPage(pageInfo.getTotalPages());
        result.setTotal(pageInfo.getTotalElements());
        result.setList(pageInfo.getContent());
        return result;
    }
}
