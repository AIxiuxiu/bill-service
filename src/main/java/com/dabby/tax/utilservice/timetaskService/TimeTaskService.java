package com.dabby.tax.utilservice.timetaskService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by Aaronchen on 2019/4/18
 *  定时任务服务
 * @author Aaronchen
 */
@Configuration
@EnableScheduling
@Slf4j
@Lazy(false)
public class TimeTaskService {

    @Async
    @Scheduled(cron = "* * * * * ?")
    public void runTaskA(){
        log.info("定时任务启动");
    }
}
