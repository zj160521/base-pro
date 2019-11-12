package com.simes.sys.service.role;

import com.simes.core.exception.BizException;
import com.simes.core.util.IDGenerator;
import com.simes.sys.dao.RoleMenuDao;
import com.simes.sys.domain.RoleMenuDO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/9 15:34
 */
@Service
public class RoleMenuService {
    @Autowired
    private RoleMenuDao dao;
    
    public void addRoleMenu(RoleMenuDO roleMenuDO){
        if (StringUtils.isEmpty(roleMenuDO.getMenuId()) && StringUtils.isEmpty(roleMenuDO.getRoleId())) {
            throw new BizException("请选择角色和菜单！");
        }
        dao.insertSelective(roleMenuDO);
    }

    public void addRoleMenus(String roleId, List<String> menuIds){
        if (StringUtils.isEmpty(roleId)) {
            throw new BizException("角色id不能为空！");
        }
        if (menuIds != null && menuIds.size() > 0) {
            List<RoleMenuDO> list = new ArrayList<>(10);
            for (String menuId :  menuIds) {
                RoleMenuDO roleMenuDO = new RoleMenuDO();
                roleMenuDO.setId(IDGenerator.uuid());
                roleMenuDO.setRoleId(roleId);
                roleMenuDO.setMenuId(menuId);
                list.add(roleMenuDO);
            }
            dao.insertRoleMenus(list);
        }
    }

    public void updateMenu(RoleMenuDO roleMenuDO){
        if (StringUtils.isEmpty(roleMenuDO.getId())) {
            throw new BizException("id不能为空！");
        }
        dao.updateByPrimaryKeySelective(roleMenuDO);
    }

    public RoleMenuDO selectOne(RoleMenuDO roleMenuDO){
        return dao.selectOne(roleMenuDO);
    }

    public List<RoleMenuDO> list(RoleMenuDO roleMenuDO){
        return dao.select(roleMenuDO);
    }

    public void deleteByRoleId(String roleId){
        if (StringUtils.isEmpty(roleId)) {
            throw new BizException("角色id不能为空！");
        }
        RoleMenuDO roleMenuDO = new RoleMenuDO();
        roleMenuDO.setRoleId(roleId);
        dao.delete(roleMenuDO);
    }
}
