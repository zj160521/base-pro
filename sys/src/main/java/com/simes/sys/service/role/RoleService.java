package com.simes.sys.service.role;

import com.simes.core.domain.Ids;
import com.simes.core.domain.sys.role.SysRoleDO;
import com.simes.core.domain.sys.user.SysUserDO;
import com.simes.core.exception.BizException;
import com.simes.core.service.BaseService;
import com.simes.core.util.IDGenerator;
import com.simes.sys.dao.RoleDao;
import com.simes.sys.domain.RoleMenuReq;
import com.simes.sys.service.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/9 15:34
 */
@Service
public class RoleService extends BaseService {
    @Autowired
    private RoleDao dao;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleMenuService roleMenuService;
    
    public void addRole(SysRoleDO roleDO, String createrId){
        if (StringUtils.isEmpty(roleDO.getRoleName())) {
            throw new BizException("角色名不能为空！");
        }
        SysUserDO user = new SysUserDO();
        user.setId(createrId);
        SysUserDO userDO = userService.selectOne(user);
        roleDO.setId(IDGenerator.uuid());
        roleDO.setTenantId(userDO.getTenantId());
        roleDO.setParentId(userDO.getRoleId());
        dao.insertSelective(roleDO);
    }

    public void updateRole(SysRoleDO roleDO){
        if (StringUtils.isEmpty(roleDO.getId())) {
            throw new BizException("id不能为空！");
        }
        dao.updateByPrimaryKeySelective(roleDO);
    }

    public SysRoleDO selectOne(SysRoleDO roleDO){
        return dao.selectOne(roleDO);
    }

    public List<SysRoleDO> list(SysRoleDO roleDO){
        return dao.select(roleDO);
    }

    public void delete(Ids ids){
        this.baseDao = dao;
        deleteByIds(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    public void setMenu(RoleMenuReq roleMenuReq) {
        List<String> ids = roleMenuReq.getMenuIds();
        if ( ids == null || ids.isEmpty()) {
            throw new BizException("角色至少拥有一项权限！");
        }
        roleMenuService.deleteByRoleId(roleMenuReq.getRoleId());
        roleMenuService.addRoleMenus(roleMenuReq.getRoleId(), roleMenuReq.getMenuIds());
    }
}
