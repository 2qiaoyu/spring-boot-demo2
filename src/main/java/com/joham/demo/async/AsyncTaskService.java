package com.joham.demo.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author joham
 */
@Service
@Slf4j
public class AsyncTaskService {

    /**
     * 通过@Async注解表明该方法是一个异步方法，如果注解在类级别，表明该类下所有方法都是异步方法，
     * 而这里的方法自动被注入使用ThreadPoolTaskExecutor 作为 TaskExecutor
     *
     * @param i
     */
    @Async
    public void executeAsyncTask(Integer i) {
        log.info("执行异步任务：" + i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i) {
        log.info("执行异步任务+1：" + (i + 1));
    }

}
