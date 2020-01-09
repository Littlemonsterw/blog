package com.monster.blog.service;

/**
 * Redis常用操作
 * 对象和数组都以json形式存储
 * @author wuhan
 * @date 2020/1/9 17:25
 */
public interface RedisService {

    /**
     * 存储数据
     * @param key  key
     * @param value value
     */
    void set(String key, String value);

    /**
     * 获取数据
     * @param key key
     * @return data
     */
    String get(String key);

    /**
     * 设置超期时间
     * @param key key
     * @param expire 超期时间
     * @return result
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     * @param key key
     */
    void remove(String key);

    /**
     * 自增操作
     * @param key key
     * @param delta 自增步长
     * @return data
     */
    Long increment(String key, long delta);
}
