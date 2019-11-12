package com.simes.core.service;

import com.simes.core.dao.BaseDao;
import com.simes.core.domain.BaseDO;
import com.simes.core.domain.Ids;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/23 11:18
 */
public class BaseService<T extends BaseDO> {
    protected BaseDao baseDao;

    protected void deleteByIds(Ids ids){
        List<String> idList = ids.getIds();
        if (idList != null && idList.size() > 0) {
            for (String id : idList) {
                baseDao.deleteByPrimaryKey(id);
            }
        }
    }

    public class TimeAscendingComparator implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.getCreateTime().compareTo(o2.getCreateTime());
        }
    }
}
