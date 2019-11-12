package com.simes.crontab.dao;


import com.simes.core.dao.BaseDao;
import com.simes.crontab.domain.JobDO;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/10/23 13:53:33
 */
@Component
public interface TaskDao extends BaseDao<JobDO> {

}