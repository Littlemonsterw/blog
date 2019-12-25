package com.monster.blog.common.api;

import java.io.Serializable;

/**
 * @author Monster
 * @date 2019/12/25.
 */
public interface IResultCode extends Serializable {

    /**
     * 状态码
     * @return
     */
    int getCode();

    /**
     * 返回信息
     * @return
     */
    String getMessage();
}
