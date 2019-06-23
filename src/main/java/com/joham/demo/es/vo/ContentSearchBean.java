package com.joham.demo.es.vo;

import lombok.Data;

/**
 * @author joham
 */
@Data
public class ContentSearchBean {

    private Integer pageNumber;

    private Integer pageSize;

    private String searchContent;

    private Integer type;

    private String category;

    private String orderName;
}
