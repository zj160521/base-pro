package com.simes.crontab.listenner;

import com.simes.crontab.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/10/23 13:57:07
 */
@Component
@Order(value = 1)
public class ScheduleJobInit implements CommandLineRunner {

    @Autowired
    private TaskService scheduleJobService;

    @Override
    public void run(String... arg0) {
        try {
            scheduleJobService.initSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}