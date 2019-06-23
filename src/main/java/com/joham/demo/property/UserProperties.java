package com.joham.demo.property;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author joham
 */
@Component
@ConfigurationProperties(prefix = "user")
@Data
@ToString
public class UserProperties {
    /**
     * 用户 ID
     */
    private Long id;

    /**
     * 年龄
     */
    private int age;

    /**
     * 用户名称
     */
    private String desc;

    /**
     * 用户 UUID
     */
    private String uuid;
}
