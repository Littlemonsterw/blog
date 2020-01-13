package com.monster.blog.common.api;

/**
 * @author Monster
 * @date 2019/12/25.
 */
public enum ResultCode implements IResultCode {
    /**
     * SUCCESS: 操作成功
     * FAILURE：操作失败
     */
    SUCCESS(200, "操作成功"),
    FAILURE(400, "操作失败"),
    PARAM_VALID_ERROR(400, "参数校验失败"),
    UNAUTHORIZED(401, "暂未登录或token已过期"),
    FORBIDDEN(403, "没有相关权限");

    final int code;
    final String message;

    ResultCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
