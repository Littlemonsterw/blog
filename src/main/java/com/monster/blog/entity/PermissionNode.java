package com.monster.blog.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author wuhan
 * @date 2020/1/14 14:09
 */
public class PermissionNode extends Permission {

    @Getter
    @Setter
    private List<PermissionNode> children;
}
