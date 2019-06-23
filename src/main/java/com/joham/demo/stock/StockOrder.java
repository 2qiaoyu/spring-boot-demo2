package com.joham.demo.stock;

import lombok.Data;

import java.util.Date;

/**
 * 订单
 *
 * @author joham
 */
@Data
public class StockOrder {

    private Integer id;

    private Integer sid;

    private String name;

    private Date createTime;
}