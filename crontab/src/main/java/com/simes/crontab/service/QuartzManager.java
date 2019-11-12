package com.simes.crontab.service;

import com.simes.core.exception.BizException;
import com.simes.crontab.domain.JobDO;
import org.quartz.*;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description: 任务管理
 * @Author: zhouj
 * @Date: 2019/10/23 13:57:07
 */
@Service
public class QuartzManager {
    @Autowired
    private Scheduler scheduler;

    /**
     * 添加任务
     * 
     * @param task
     * @throws SchedulerException
     */    
    public void addJob(JobDO task) {
        try {
            // 指明job的名称，所在组的名称，以及绑定job类
            Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(task.getBeanClass())
                    .newInstance()
                    .getClass());
            // 创建jobDetail实例，绑定Job实现类
            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    // 任务名称和组构成任务key
                    .withIdentity(task.getJobName(), task.getJobGroup())
                    .build();
            // 定义调度触发规则
            // 使用cornTrigger规则
            Trigger trigger = TriggerBuilder.newTrigger()
                    // 触发器key
                    .withIdentity(task.getJobName(), task.getJobGroup())
                    .startAt(DateBuilder.futureDate(1, IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(task.getCronExpression()))
                    .startNow()
                    .build();

            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new BizException("任务已启动，请勿重复启动！");
        }
    }

    /**
     * 获取所有计划中的任务列表
     * 
     * @return
     * @throws SchedulerException
     */
    public List<JobDO> getAllJob() throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<JobDO> jobList = new ArrayList<JobDO>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                JobDO job = new JobDO();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setDescription("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setJobStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }

    /**
     * 获取正在运行的job
     * 
     * @return
     * @throws SchedulerException
     */
    public List<JobDO> getRunningJob() throws SchedulerException {
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<JobDO> jobList = new ArrayList<JobDO>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            JobDO job = new JobDO();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setJobName(jobKey.getName());
            job.setJobGroup(jobKey.getGroup());
            job.setDescription("触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setJobStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
            jobList.add(job);
        }
        return jobList;
    }

    /**
     * 暂停一个job
     * 
     * @param task
     * @throws SchedulerException
     */
    public void pauseJob(JobDO task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复一个job
     * 
     * @param task
     * @throws SchedulerException
     */
    public void resumeJob(JobDO task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除一个job
     * 
     * @param task
     * @throws SchedulerException
     */
    public void deleteJob(JobDO task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.deleteJob(jobKey);

    }

    /**
     * 立即执行job
     * 
     * @param task
     * @throws SchedulerException
     */
    public void runJobNow(JobDO task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 更新job时间表达式
     * 
     * @param task
     * @throws SchedulerException
     */
    public void updateJobCron(JobDO task) throws SchedulerException {
        if (task.getJobName().isEmpty() || task.getJobGroup().isEmpty()) {
            throw new BizException("任务名、任务组不能为空！");
        }
        TriggerKey triggerKey = TriggerKey.triggerKey(task.getJobName(), task.getJobGroup());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCronExpression());

        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        scheduler.rescheduleJob(triggerKey, trigger);
    }
}