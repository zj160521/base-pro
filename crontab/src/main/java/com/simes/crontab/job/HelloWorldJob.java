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
 * @Date: 2019/10/24 14:01
 */
@DisallowConcurrentExecution
@Component
public class HelloWorldJob implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("This is a crontab," + new Date());
    }

}