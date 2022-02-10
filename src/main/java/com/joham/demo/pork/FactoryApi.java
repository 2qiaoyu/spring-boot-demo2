package com.joham.demo.pork;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FactoryApi {

    public void supplyPork(Long weight) {
        log.info("call real factory to supply pork, weight: {}", weight);
    }
}
