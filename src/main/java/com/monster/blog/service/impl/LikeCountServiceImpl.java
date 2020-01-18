package com.monster.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monster.blog.entity.LikeCount;
import com.monster.blog.mapper.LikeCountMapper;
import com.monster.blog.service.LikeCountService;
import org.springframework.stereotype.Service;

/**
 * @author wuhan
 * @date 2020/1/18 11:03
 */
@Service
public class LikeCountServiceImpl extends ServiceImpl<LikeCountMapper, LikeCount> implements LikeCountService {
}
