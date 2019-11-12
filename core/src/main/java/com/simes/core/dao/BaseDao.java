package com.simes.core.dao;

import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/09/23 14:33:28
 */
@Component
public interface BaseDao<T> extends Mapper<T> {
    /**
     * 通过主键删除记录
     * @param ids id数组
     * @param tableName 表名
     */
    @Delete("<script> DELETE FROM ${tableName} WHERE id = " +
            "  <foreach collection='ids' item='id' separator='or' > " +
            "  (#{id})\n" +
            "  </foreach> </script>")
    void deleteByIds(List<String> ids, String tableName);
}
