package com.joham.demo.es.controller;

import com.joham.demo.es.domain.City;
import com.joham.demo.es.ik.IkUtil;
import com.joham.demo.es.service.CityService;
import com.joham.demo.es.vo.ContentSearchBean;
import com.joham.demo.es.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author joham
 * 城市 Controller 实现 Restful HTTP 服务
 */
@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;

    @Autowired
    private IkUtil ikUtil;

    /**
     * 插入 ES 新城市
     *
     * @param city
     * @return
     */
    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public Long createCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }

    /**
     * 获取ES所有城市
     *
     * @return
     */
    @GetMapping(value = "/api/city")
    public List<City> getCity() {
        return cityService.getCity().getContent();
    }

    /**
     * AND 语句查询
     *
     * @param description
     * @param provinceId
     * @return
     */
    @RequestMapping(value = "/api/city/and/find", method = RequestMethod.GET)
    public List<City> findByDescriptionAndScore(@RequestParam(value = "description") String description,
                                                @RequestParam(value = "provinceId") Integer provinceId) {
        return cityService.findByDescriptionAndProvinceId(description, provinceId);
    }

    /**
     * OR 语句查询
     *
     * @param description
     * @param provinceId
     * @return
     */
    @RequestMapping(value = "/api/city/or/find", method = RequestMethod.GET)
    public List<City> findByDescriptionOrScore(@RequestParam(value = "description") String description,
                                               @RequestParam(value = "provinceId") Integer provinceId) {
        return cityService.findByDescriptionOrProvinceId(description, provinceId);
    }

    /**
     * 查询城市描述
     *
     * @param description
     * @return
     */
    @RequestMapping(value = "/api/city/description/find", method = RequestMethod.GET)
    public List<City> findByDescription(@RequestParam(value = "description") String description) {
        return cityService.findByDescription(description);
    }

    /**
     * NOT 语句查询
     *
     * @param description
     * @return
     */
    @RequestMapping(value = "/api/city/description/not/find", method = RequestMethod.GET)
    public List<City> findByDescriptionNot(@RequestParam(value = "description") String description) {
        return cityService.findByDescriptionNot(description);
    }

    /**
     * LIKE 语句查询
     *
     * @param description
     * @return
     */
    @RequestMapping(value = "/api/city/like/find", method = RequestMethod.GET)
    public List<City> findByDescriptionLike(@RequestParam(value = "description") String description) {
        return cityService.findByDescriptionLike(description);
    }

    /**
     * ik分词结果
     *
     * @param searchContent
     * @return
     */
    @GetMapping(value = "/api/city/ik/content")
    public List<String> handlingSearchContent(String searchContent, String type) {
        if(StringUtils.isEmpty(searchContent)) {
            return new ArrayList<>();
        }
        return ikUtil.handlingSearchContent(searchContent, type);
    }

    /**
     * 通过ik分词器搜搜
     *
     * @param searchContent 搜索内容
     * @param type          分词类型
     * @return
     */
    @GetMapping(value = "/api/city/ik/find")
    public PageBean findByIk(String searchContent, String type) {
        ContentSearchBean contentSearchBean = new ContentSearchBean();
        contentSearchBean.setSearchContent(searchContent);
        contentSearchBean.setPageNumber(0);
        contentSearchBean.setPageSize(15);
        return cityService.searchContent(contentSearchBean, type);
    }
}
