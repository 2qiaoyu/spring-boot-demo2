package com.joham.demo.priorityqueue.bean;

import lombok.Data;

/**
 * @author joham
 */
@Data
public class PriorityQueueMessageRequestBean {

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
     * 优先级
     */
    private Integer priority;

    /**
     * 内容
     */
    private String content;

}
