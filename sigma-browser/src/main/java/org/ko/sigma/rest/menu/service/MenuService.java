package org.ko.sigma.rest.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ko.sigma.data.entity.Menu;
import org.ko.sigma.rest.menu.condition.MenuQueryListCondition;
import org.ko.sigma.rest.menu.dto.MenuDTO;

import java.util.List;

public interface MenuService extends IService<Menu> {

    /**
     * <p>查询菜单列表</p>
     * @param condition
     * @return
     */
    List<MenuDTO> queryMenuList(MenuQueryListCondition condition);

    /**
     * <p>通过主键查询菜单</p>
     * @param id
     * @return
     */
    MenuDTO queryMenuInfo(Long id);


    List<MenuDTO> queryMenuByParentId(Long id);

    /**
     * <p>创建新的菜单</p>
     * @param menu
     */
    Long createMenu(Menu menu);

    /**
     * <p>通过ID更新菜单</p>
     * @param id Menu Id
     * @param menu 菜单对象
     * @return
     */
    Menu updateMenu(Long id, Menu menu);

    /**
     * <p>删除菜单</p>
     * @param id Menu主键id
     * @return
     */
    Long deleteMenu(Long id);


    /**
     * 通过权限ID查询菜单列表
     * @param roleId
     * @return
     */
    List<MenuDTO> queryMenuByRoleId(Long roleId);
}
