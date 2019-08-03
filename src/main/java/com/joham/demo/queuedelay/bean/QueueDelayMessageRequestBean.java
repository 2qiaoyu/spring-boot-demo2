package com.joham.demo.queuedelay.bean;

import lombok.Data;

/**
 * @author joham
 */
@Data
public class QueueDelayMessageRequestBean {

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

    /**
     * 延迟时间
     */
    private String delayTime;

}
