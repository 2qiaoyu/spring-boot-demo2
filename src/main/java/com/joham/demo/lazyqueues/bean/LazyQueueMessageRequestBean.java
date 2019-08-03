package com.joham.demo.lazyqueues.bean;

import lombok.Data;

/**
 * @author joham
 */
@Data
public class LazyQueueMessageRequestBean {

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
