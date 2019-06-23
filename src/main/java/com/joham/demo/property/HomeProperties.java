package com.joham.demo.property;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 家乡属性
 *
 * @author joham
 */
@Component
@ConfigurationProperties(prefix = "home")
@Data
@ToString
public class HomeProperties {

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 描述
     */
    private String desc;
}
