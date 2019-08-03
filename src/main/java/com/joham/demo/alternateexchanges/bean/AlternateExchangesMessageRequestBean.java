package com.joham.demo.alternateexchanges.bean;

import lombok.Data;

/**
 * @author joham
 */
@Data
public class AlternateExchangesMessageRequestBean {

    /**
     * 交换器
     */
    private String exchange;

    /**
     * 路由键
     */
    private String routingKey;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 内容
     */
    private String content;

}
