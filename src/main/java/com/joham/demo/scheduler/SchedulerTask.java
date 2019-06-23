package com.joham.demo.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器
 *
 * @author joham
 */
@Component
@Slf4j
public class SchedulerTask {

    private int count = 0;

    @Scheduled(cron = "* * 1 * * ?")
    private void process() {
        log.info("this is scheduler task running  " + (count++));
    }
}
