package com.joham.demo.stopwatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joham
 */
@RequestMapping("/StopWatch")
@RestController
@Slf4j
public class StopWatchController {

    @Autowired
    private StopWatchService stopWatchService;

    @RequestMapping("/")
    public void stopWatchTest() {
        StopWatch stopWatch = new StopWatch("stopwatch test");


        stopWatch.start("执行本地方法");
        stopWatchService.executeNative();
        stopWatch.stop();


        stopWatch.start("执行数据库操作");
        stopWatchService.executeDB();
        stopWatch.stop();


        stopWatch.start("执行远程调用");
        stopWatchService.executeRPC();
        stopWatch.stop();


        log.info(stopWatch.prettyPrint());

        log.info("\n");


        log.info(stopWatch.shortSummary());

        log.info("\n");


        log.info(stopWatch.getTaskInfo().toString());
    }
}
