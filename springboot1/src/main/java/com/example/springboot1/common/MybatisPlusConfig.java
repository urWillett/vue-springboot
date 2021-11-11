package com.example.springboot1.common;
//mybatis-plus 分页插件

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    /**
     * 分页插件
     */

    @Bean
    public paginationInterceptor paginationInterceptor() {return new PaginationInnerInterceptor
    }
}
