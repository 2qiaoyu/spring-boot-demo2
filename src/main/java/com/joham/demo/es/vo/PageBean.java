package com.joham.demo.es.vo;

import lombok.Data;

import java.util.List;

/**
 * @param <T>
 * @author joham
 */
@Data
public class PageBean<T> {

    private Integer pageNumber;

    private Integer pageSize;

    private Integer totalCount;

    private Integer totalPage;

    private List result;
}
