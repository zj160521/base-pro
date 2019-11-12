package com.simes.crontab.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/10/24 15:01
 */
@DisallowConcurrentExecution
@Component
public class TestJob implements Job {

    @Override
    public void execute(JobExecutionContext arg0) {
        System.out.println("test");
    }

}