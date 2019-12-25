package com.monster.blog.common.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Monster
 * @date 2019/12/25.
 */
@ApiModel(description = "通用返回信息")
public class R<T> implements Serializable {

    @ApiModelProperty(value = "状态码", required = true)
    private int code;

    @ApiModelProperty(value = "返回信息", required = true)
    private String msg;

    @ApiModelProperty("承载数据")
    private T data;

    protected R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> R<T> success(T data) {
        return new R<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> R<T> success(T data, String msg) {
        return new R<T>(ResultCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> R<T> failed(IResultCode errorCode) {
        return new R<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static <T> R<T> failed(String msg) {
        return new R<T>(ResultCode.FAILURE.getCode(), msg, null);
    }

    public static <T> R<T> failed() {
        return failed(ResultCode.FAILURE);
    }

    public static <T> R<T> validateFailed() {
        return failed(ResultCode.PARAM_VALID_ERROR);
    }

    public static <T> R<T> validateFailed(String msg) {
        return new R<T>(ResultCode.PARAM_VALID_ERROR.getCode(), msg, null);
    }

    public static <T> R<T> unAuthorized(T data) {
        return new R<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    public static <T> R<T> forbidden(T data) {
        return new R<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public R() {

    }
}
