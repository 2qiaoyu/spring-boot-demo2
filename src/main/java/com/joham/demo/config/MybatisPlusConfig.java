package com.joham.demo.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.joham.demo")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor pageInnerInterceptor() {
        MybatisPlusInterceptor paginationInnerInterceptor = new MybatisPlusInterceptor();
        paginationInnerInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return paginationInnerInterceptor;
    }
}
