package com.joham.demo.pork;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class WareHouseApi {

    public PorkInst packagePork(Long weight, Map<String, Object> params) {
        log.info("call real warehouse to package, weight: {}", weight);
        return PorkInst.builder().weight(weight).paramsMap(params).build();
    }
}
