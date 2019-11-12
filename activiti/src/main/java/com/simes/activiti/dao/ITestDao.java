package com.simes.activiti.dao;


import com.simes.core.domain.test.TestDO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 14:24
 */
@Component
public interface ITestDao extends Mapper<TestDO> {
}
