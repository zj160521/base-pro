package com.simes.sys.service.tenant;

import com.simes.core.domain.Ids;
import com.simes.core.domain.sys.tenant.SysTenantDO;
import com.simes.core.exception.BizException;
import com.simes.core.service.BaseService;
import com.simes.core.util.IDGenerator;
import com.simes.sys.dao.TenantDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/9 15:34
 */
@Service
public class TenantService extends BaseService {
    @Autowired
    private TenantDao dao;
    
    public void addTenant(SysTenantDO tenantDO){
        if (StringUtils.isEmpty(tenantDO.getTenantId())) {
            throw new BizException("租户号不能为空！");
        }
        if (StringUtils.isEmpty(tenantDO.getTenantName())) {
            throw new BizException("租户名不能为空！");
        }
        tenantDO.setId(IDGenerator.uuid());
        dao.insertSelective(tenantDO);
    }

    public void updateUser(SysTenantDO tenantDO){
        if (StringUtils.isEmpty(tenantDO.getId())) {
            throw new BizException("id不能为空！");
        }
        dao.updateByPrimaryKeySelective(tenantDO);
    }

    public List<SysTenantDO> list(SysTenantDO tenantDO){
        return dao.select(tenantDO);
    }

    public SysTenantDO selectOne(SysTenantDO tenantDO){
        return dao.selectOne(tenantDO);
    }

    public void delete(Ids ids){
        this.baseDao = dao;
        deleteByIds(ids);
    }
}
