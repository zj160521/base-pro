package com.simes.sys.dao;


import com.simes.core.dao.BaseDao;
import com.simes.sys.domain.MenuDO;
import com.simes.sys.domain.RoleMenuDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/18 14:24
 */
@Component
public interface RoleMenuDao extends BaseDao<RoleMenuDO> {
    /**
     * 为角色添加权限
     * @param roleMenuList
     */
    @Insert("<script> insert into sys_role_menu (id, role_id, menu_id) values  " +
            "  <foreach collection='roleMenuDOList' item='item' separator=',' > " +
            "  (#{item.id},#{item.roleId},#{item.menuId})\n" +
            "  </foreach> </script>")
    void insertRoleMenus(@Param(value = "roleMenuDOList") List<RoleMenuDO> roleMenuList);

    /**
     * 通过角色id查询菜单
     * @param roleId
     * @return
     */
    @Select("SELECT mn.id,mn.create_user,mn.update_user,mn.update_time,mn.create_time,mn.status,mn.is_deleted,parent_id,code,name,alias,path,source,sort,category,action,is_open,remark \n" +
            "FROM sys_role_menu rm\n" +
            "left join sys_menu mn on rm.menu_id = mn.id\n" +
            "where rm.role_id = #{roleId} ORDER BY mn.create_time")
    List<MenuDO> selectMenusByRoleId(@Param(value = "roleId") String roleId);
}
