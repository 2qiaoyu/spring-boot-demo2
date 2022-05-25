package com.joham.demo;

import io.micrometer.core.instrument.MeterRegistry;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author joham
 */
@EnableScheduling
@SpringBootApplication
@MapperScan("com.joham.demo")
@ServletComponentScan
@EnableCaching
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        //Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错解决netty冲突后初始化client时还会抛出异常
        // java.lang.IllegalStateException: availableProcessors is already set to [8], rejecting [8]
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(
            @Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }
}
