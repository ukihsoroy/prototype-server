package org.ko.sigma.rest.menu.repository;

import org.ko.sigma.data.master.entity.Menu;
import org.ko.sigma.rest.menu.condition.MenuQueryListCondition;
import org.ko.sigma.rest.menu.dto.MenuDTO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuRepository extends Mapper<Menu> {

    List<MenuDTO> queryMenuList(MenuQueryListCondition condition);

    MenuDTO queryMenuById(Long id);

    List<MenuDTO> queryMenuByParentId(Long id);

    List<MenuDTO> queryMenuByRoleId(Long roleId);
}
