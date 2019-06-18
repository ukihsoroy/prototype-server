package org.ko.sigma.rest.menu.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ko.sigma.data.entity.Menu;
import org.ko.sigma.rest.menu.condition.MenuQueryListCondition;
import org.ko.sigma.rest.menu.dto.MenuDTO;

import java.util.List;

public interface MenuRepository extends BaseMapper<Menu> {

    List<MenuDTO> queryMenuList(MenuQueryListCondition condition);

    List<MenuDTO> queryMenuByParentId(Long id);

    List<MenuDTO> queryMenuByRoleCode(String roleCode);
}
