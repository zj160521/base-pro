package com.simes.sys.service.department;


import com.simes.core.service.BaseService;
import com.simes.core.domain.Ids;
import com.simes.core.exception.BizException;
import com.simes.core.util.IDGenerator;
import org.apache.commons.lang.StringUtils;
import com.simes.sys.domain.DeptDO;
import com.simes.sys.dao.DeptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/09/23 14:33:28
 */
@Service
public class DeptService extends BaseService {
    @Autowired
    private DeptDao dao;
    
    public void add(DeptDO sysDeptDO){
        if (StringUtils.isEmpty(sysDeptDO.getTenantId())) {
            throw new BizException("租户不能为空！");
        }
        if (StringUtils.isEmpty(sysDeptDO.getDeptName())) {
            throw new BizException("部门名不能为空！");
        }
        sysDeptDO.setId(IDGenerator.uuid());
        int res = dao.insertSelective(sysDeptDO);
        if (1 != res) {
            throw new BizException("新增部门出错！");
        }
    }

    public void update(DeptDO sysDeptDO){
        if (StringUtils.isEmpty(sysDeptDO.getId())) {
            throw new BizException("id不能为空！");
        }
        int res = dao.updateByPrimaryKeySelective(sysDeptDO);
        if (1 != res) {
            throw new BizException("更新部门出错！");
        }
    }

    public List<DeptDO> list(DeptDO sysDeptDO){
        return dao.select(sysDeptDO);
    }
    
    public DeptDO selectOne(DeptDO sysDeptDO){
        return dao.selectOne(sysDeptDO);
    }

    public void delete(Ids ids){
        this.baseDao = dao;
        deleteByIds(ids);
    }
}