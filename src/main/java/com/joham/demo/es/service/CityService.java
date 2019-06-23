package com.joham.demo.es.service;

import com.alibaba.fastjson.JSON;
import com.joham.demo.es.domain.City;
import com.joham.demo.es.ik.IkUtil;
import com.joham.demo.es.repository.CityRepository;
import com.joham.demo.es.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * 城市 ES 业务接口类
 *
 * @author joham
 */
@Service
@Slf4j
public class CityService {

    @Autowired
    private IkUtil ikUtil;

    Pageable pageable = PageRequest.of(0, 10);

    @Autowired
    CityRepository cityRepository;

    public Long saveCity(City city) {
        City cityResult = cityRepository.save(city);
        return cityResult.getId();
    }

    public List<City> findByDescriptionAndProvinceId(String description, Integer provinceId) {
        return cityRepository.findByDescriptionAndProvinceId(description, provinceId);
    }

    public List<City> findByDescriptionOrProvinceId(String description, Integer provinceId) {
        return cityRepository.findByDescriptionOrProvinceId(description, provinceId);
    }

    public List<City> findByDescription(String description) {
        return cityRepository.findByDescription(description, pageable).getContent();
    }

    public List<City> findByDescriptionNot(String description) {
        return cityRepository.findByDescriptionNot(description, pageable).getContent();
    }

    public List<City> findByDescriptionLike(String description) {
        return cityRepository.findByDescriptionLike(description, pageable).getContent();
    }

    /**
     * 构造查询条件
     */
    private void buildMatchQuery(BoolQueryBuilder queryBuilder, List<String> searchTermList) {
        for (String searchTerm : searchTermList) {
            for (ContentSearchTermEnum searchTermEnum : ContentSearchTermEnum.values()) {
                queryBuilder.should(QueryBuilders.matchPhraseQuery(searchTermEnum.getName(), searchTerm));
            }
        }
        queryBuilder.minimumShouldMatch(SearchConstant.MINIMUM_SHOULD_MATCH);
    }

    /**
     * 构建筛选条件
     */
    private void buildFilterQuery(BoolQueryBuilder boolQueryBuilder, Integer type, String category) {
        // 内容类型筛选
        if (type != null) {
            BoolQueryBuilder typeFilterBuilder = QueryBuilders.boolQuery();
            typeFilterBuilder.should(QueryBuilders.matchQuery(SearchConstant.TYPE_NAME, type).lenient(true));
            boolQueryBuilder.filter(typeFilterBuilder);
        }

        // 内容类别筛选
        if (!StringUtils.isEmpty(category)) {
            BoolQueryBuilder categoryFilterBuilder = QueryBuilders.boolQuery();
            categoryFilterBuilder.should(QueryBuilders.matchQuery(SearchConstant.CATEGORY_NAME, category).lenient(true));
            boolQueryBuilder.filter(categoryFilterBuilder);
        }
    }

    /**
     * 搜索方法
     *
     * @param contentSearchBean
     * @param type
     * @return
     */
    public PageBean searchContent(ContentSearchBean contentSearchBean, String type) {

        Integer pageNumber = contentSearchBean.getPageNumber();
        Integer pageSize = contentSearchBean.getPageSize();

        PageBean<ContentEntity> resultPageBean = new PageBean<>();
        resultPageBean.setPageNumber(pageNumber);
        resultPageBean.setPageSize(pageSize);

        // 构建搜索短语
        String searchContent = contentSearchBean.getSearchContent();
        List<String> searchTermList = ikUtil.handlingSearchContent(searchContent, type);

        // 构建查询条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        buildMatchQuery(boolQueryBuilder, searchTermList);

        // 构建筛选条件
        buildFilterQuery(boolQueryBuilder, contentSearchBean.getType(), contentSearchBean.getCategory());

        // 构建分页、排序条件
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (!StringUtils.isEmpty(contentSearchBean.getOrderName())) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, contentSearchBean.getOrderName());
        }
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
                .withQuery(boolQueryBuilder).build();
        log.info(boolQueryBuilder.toString());
        Page<City> contentPage = cityRepository.search(searchQuery);

        resultPageBean.setResult(contentPage.getContent());
        resultPageBean.setTotalCount((int) contentPage.getTotalElements());
        resultPageBean.setTotalPage((int) contentPage.getTotalElements() / resultPageBean.getPageSize() + 1);
        return resultPageBean;
    }
}