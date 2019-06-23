package com.joham.demo.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joham
 */
@RestController
public class AsyncTaskController {

    @Autowired
    private AsyncTaskService asyncTaskService;

    @GetMapping("asyncTask")
    public String asyncTask() {
        for (int i = 0; i < 10; i++) {
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }
        return "1";
    }
}
