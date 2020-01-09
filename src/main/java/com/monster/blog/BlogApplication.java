package com.monster.blog;

import com.monster.blog.common.api.R;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Monster
 */
@SpringBootApplication
@MapperScan("com.monster.blog.mapper")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @GetMapping("/")
    public R checkServerStatus() {
        return R.success("服务器在线！");
    }

}
