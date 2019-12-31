package com.monster.blog.common.en;

import lombok.Getter;

/**
 * @author wuhan
 * @date 2019/12/31 14:40
 */
@Getter
public enum StatusEnum {

    UN_ACTIVE(0,"未激活"),
    ACTIVE(1,"已激活");

    private int status;

    private String desc;

    StatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
