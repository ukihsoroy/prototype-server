package org.ko.prototype.rest.menu.service;

import org.ko.prototype.data.master.domain.Menu;
import org.ko.prototype.rest.menu.condition.MenuQueryListCondition;

import java.util.List;

public interface MenuService {

    /**
     * <p>查询菜单列表</p>
     * @param condition
     * @return
     */
    List<Menu> queryMenuList(MenuQueryListCondition condition);

    /**
     * <p>通过主键查询菜单</p>
     * @param id
     * @return
     */
    Menu queryMenuInfo(Long id);

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


}
