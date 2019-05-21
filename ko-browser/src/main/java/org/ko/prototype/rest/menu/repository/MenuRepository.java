package org.ko.prototype.rest.menu.repository;

import org.ko.prototype.data.master.domain.Menu;
import org.ko.prototype.rest.menu.condition.MenuQueryListCondition;
import org.ko.prototype.rest.menu.dto.MenuDTO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuRepository extends Mapper<Menu> {

    List<MenuDTO> queryMenuList(MenuQueryListCondition condition);

    MenuDTO queryMenuById(Long id);

    List<MenuDTO> queryMenuByParentId(Long id);

    List<MenuDTO> queryMenuByRoleId(Long roleId);
}
