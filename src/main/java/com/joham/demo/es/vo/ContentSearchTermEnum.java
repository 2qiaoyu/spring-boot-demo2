package com.joham.demo.es.vo;

/**
 * @author joham
 */
public enum ContentSearchTermEnum {

    // 标题
    CITY_NAME("cityName"),

    // 内容
    DESCRIPTION("description");

    /**
     * 搜索字段
     */
    private String name;

    ContentSearchTermEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
