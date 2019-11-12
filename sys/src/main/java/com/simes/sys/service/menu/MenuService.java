package com.simes.sys.service.menu;

import com.simes.core.domain.Ids;
import com.simes.core.domain.sys.menu.SysMenuVO;
import com.simes.core.domain.sys.user.SysUserDO;
import com.simes.core.exception.BizException;
import com.simes.core.service.BaseService;
import com.simes.core.util.IDGenerator;
import com.simes.sys.dao.MenuDao;
import com.simes.sys.dao.RoleMenuDao;
import com.simes.sys.domain.MenuDO;
import com.simes.sys.service.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/9 15:34
 */
@Service
public class MenuService extends BaseService {
    @Autowired
    private MenuDao dao;
    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private UserService userService;
    private final String topMenuId = "0";
    
    public void addMenu(MenuDO menuDO){
        if (StringUtils.isEmpty(menuDO.getCode())) {
            throw new BizException("菜单编号不能为空！");
        }
        if (StringUtils.isEmpty(menuDO.getName())) {
            throw new BizException("菜单名称不能为空！");
        }
        if (menuDO.getSort() == null) {
            throw new BizException("排序不能为空！");
        }
        menuDO.setId(IDGenerator.uuid());
        dao.insertSelective(menuDO);
    }

    public void updateMenu(MenuDO menuDO){
        if (StringUtils.isEmpty(menuDO.getId())) {
            throw new BizException("id不能为空！");
        }
        dao.updateByPrimaryKeySelective(menuDO);
    }

    public MenuDO selectOne(MenuDO menuDO){
        return dao.selectOne(menuDO);
    }

    public List<SysMenuVO> listMenuByUserId(String userId){
        if (StringUtils.isEmpty(userId)) {
            throw new BizException("userId不能为空！");
        }
        SysUserDO user = new SysUserDO();
        user.setId(userId);
        SysUserDO userDO = userService.selectOne(user);
        String roleId = userDO.getRoleId();
        return listMenuByRoleId(roleId);
    }

    public List<SysMenuVO> listMenuByRoleId(String roleId){
        if (StringUtils.isEmpty(roleId)) {
            throw new BizException("roleId不能为空！");
        }
        List<MenuDO> menuList = roleMenuDao.selectMenusByRoleId(roleId);
        Map<String, List<MenuDO>> parentMap = getParentMap(menuList);
        return getMenuTree(parentMap, topMenuId);
    }

    public void delete(Ids ids){
        this.baseDao = dao;
        deleteByIds(ids);
    }

    /**
     * 将菜单集合转化为key为parentId,value为List<MenuDO>的map
     * @param menuList 菜单集合
     * @return
     */
    private Map<String, List<MenuDO>> getParentMap(List<MenuDO> menuList) {
        if (menuList == null || menuList.size() == 0) {
            return new HashMap<>(16);
        }
        Map<String, List<MenuDO>> menuMap = new HashMap<>(16);
        for (MenuDO menu : menuList) {
            String parentId = menu.getParentId();
            List<MenuDO> menus = menuMap.get(parentId);
            if (menus == null) {
                menus = new ArrayList<>(10);
            }
            menus.add(menu);
            menuMap.put(parentId, menus);
        }
        return menuMap;
    }

    /**
     * 获取树状结构菜单列表
     * @param parentMap
     * @param parenId
     * @return
     */
    private List<SysMenuVO> getMenuTree(Map<String, List<MenuDO>> parentMap, String parenId) {
        List<SysMenuVO> resultList = new ArrayList<>(10);
        if (parentMap.isEmpty()) {
            return resultList;
        }
        List<MenuDO> currentLevelMenuList = parentMap.get(parenId);
        if (currentLevelMenuList == null || currentLevelMenuList.isEmpty()) {
            return resultList;
        }
        for (MenuDO currentLevelMenu : currentLevelMenuList) {
            SysMenuVO menuVO = new SysMenuVO();
            BeanUtils.copyProperties(currentLevelMenu, menuVO);
            List<SysMenuVO> childrenVO = getMenuTree(parentMap, currentLevelMenu.getId());
            menuVO.setChildren(childrenVO);
            resultList.add(menuVO);
        }
        return resultList;
    }
}
