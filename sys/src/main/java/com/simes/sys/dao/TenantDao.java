package com.simes.sys.dao;


import com.simes.core.dao.BaseDao;
import com.simes.core.domain.sys.tenant.SysTenantDO;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 14:24
 */
@Component
public interface TenantDao extends BaseDao<SysTenantDO> {
}
