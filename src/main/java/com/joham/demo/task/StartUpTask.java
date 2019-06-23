package com.joham.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author joham
 */
@Component
@Order(1)
@Slf4j
public class StartUpTask implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("The Runner start to initialize ...");
    }
}
