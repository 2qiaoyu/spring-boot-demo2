package com.joham.demo.pork;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class PorkService {

    @Autowired
    private PorkStorageDao porkStorageDao;

    /**
     * 获取猪肉打包实例
     *
     * @param weight 重量
     * @param params 额外信息
     * @return {@link PorkInst} - 指定数量的猪肉实例
     */
    PorkInst getPork(Long weight, Map<String, Object> params) {
        log.info("重量" + weight.toString());
        PorkStorage porkStorage = porkStorageDao.queryStore();
        return new PorkInst(porkStorage.getCnt(), params);
    }
}
