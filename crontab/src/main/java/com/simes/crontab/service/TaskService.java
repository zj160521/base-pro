package com.simes.crontab.service;

import com.simes.core.domain.Ids;
import com.simes.core.exception.BizException;
import com.simes.core.service.BaseService;
import com.simes.core.util.IDGenerator;
import com.simes.crontab.dao.TaskDao;
import com.simes.crontab.domain.JobDO;
import com.simes.crontab.enums.JobStatusEnum;
import org.apache.commons.lang.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/10/23 13:57:07
 */
@Service
public class TaskService extends BaseService {
    @Autowired
    private TaskDao dao;
    @Autowired
    private QuartzManager quartzManager;
    
    public void save(JobDO taskDO){
        if (StringUtils.isEmpty(taskDO.getJobName())) {
            throw new BizException("任务名不能为空！");
        }
        if (StringUtils.isEmpty(taskDO.getJobGroup())) {
            throw new BizException("任务组不能为空！");
        }
        if (StringUtils.isEmpty(taskDO.getCronExpression())) {
            throw new BizException("任务表达式不能为空！");
        }
        if (StringUtils.isEmpty(taskDO.getBeanClass())) {
            throw new BizException("任务类路径不能为空！");
        }
        taskDO.setId(IDGenerator.uuid());
        int res = dao.insertSelective(taskDO);
        if (1 != res) {
            throw new BizException("新增定时任务出错！");
        }
        quartzManager.addJob(taskDO);
    }

    public void update(JobDO taskDO){
        if (taskDO == null || StringUtils.isEmpty(taskDO.getId())) {
            throw new BizException("id不能为空！");
        }
        // 表达式更新
        if (!StringUtils.isEmpty(taskDO.getCronExpression())){
            JobDO task = getTaskByid(taskDO.getId());
            if (!taskDO.getCronExpression().trim().equals(task.getCronExpression().trim())){
                task.setCronExpression(taskDO.getCronExpression());
                try {
                    quartzManager.updateJobCron(task);
                } catch (SchedulerException e) {
                    throw new BizException("更新表达式出错！");
                }
            }
        }
        // jobName和jobGroup构成triggerKey不能修改
        taskDO.setJobName(null);
        taskDO.setJobGroup(null);
        int res = dao.updateByPrimaryKeySelective(taskDO);
        if (1 != res) {
            throw new BizException("更新定时任务出错！");
        }
    }

    public List<JobDO> list(JobDO taskDO){
        if (taskDO == null) {
            taskDO = new JobDO();
        }
        return dao.select(taskDO);
    }
    
    public JobDO selectOne(JobDO taskDO){
        if (taskDO == null) {
            taskDO = new JobDO();
        }
        return dao.selectOne(taskDO);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Ids ids){
        if (ids == null) {
            new BizException("参数不能为空！");
        }
        List<String> idList = ids.getIds();
        if (idList != null && idList.size() > 0) {
            for (String id : idList) {
                JobDO taskDO = getTaskByid(id);
                try {
                    dao.deleteByPrimaryKey(id);
                    quartzManager.deleteJob(taskDO);
                } catch (SchedulerException e) {
                    throw new BizException("删除任务出错！");
                }
            }
        }
    }

    /**
     * 初始化操作将没添加进触发器的处于开启状态的任务添加进去
     * @throws SchedulerException
     */
    public void initSchedule() throws SchedulerException {
        List<JobDO> jobList = dao.selectAll();
        List<JobDO> allJob = quartzManager.getAllJob();
        Set<String> set = new HashSet<>();
        if (allJob != null && allJob.size() > 0) {
            for (JobDO taskDO : allJob) {
                set.add(taskDO.getJobName() + "." + taskDO.getJobGroup());
            }
        }
        if (jobList != null && jobList.size() > 0) {
            for (JobDO task : jobList) {
                String key = task.getJobName() + "."  + task.getJobGroup();
                if (!set.contains(key)) {
                    quartzManager.addJob(task);
                }
            }
        }
    }

    public void changeStatus(JobDO para) {
        if (StringUtils.isEmpty(para.getId())) {
            throw new BizException("任务ID不能为空！");
        }
        if (StringUtils.isEmpty(para.getJobStatus())) {
            throw new BizException("任务状态不能为空！");
        }
        String jobId = para.getId();
        String jobStatus = para.getJobStatus();
        JobDO job = getTaskByid(jobId);
        if (JobStatusEnum.STOP.getKey().equals(jobStatus)) {
            try {
                quartzManager.pauseJob(job);
            } catch (SchedulerException e) {
                throw new BizException("暂停定时任务出错！");
            }
            job.setJobStatus(JobStatusEnum.STOP.getKey());
        } else if (JobStatusEnum.RUNNING.getKey().equals(jobStatus)) {
            job.setJobStatus(jobStatus);
            try {
                quartzManager.runJobNow(job);
            } catch (SchedulerException e) {
                throw new BizException("启动定时任务出错！");
            }
        }
        JobDO taskDO = new JobDO();
        taskDO.setId(jobId);
        taskDO.setJobStatus(jobStatus);
        update(job);
    }

    private JobDO getTaskByid (String jobId) {
        JobDO taskDO = new JobDO();
        taskDO.setId(jobId);
        return selectOne(taskDO);
    }
}