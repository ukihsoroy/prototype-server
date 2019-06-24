package org.ko.sigma.rest.menu.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.ko.sigma.data.entity.Menu;
import org.ko.sigma.rest.menu.condition.MenuQueryPageCondition;
import org.ko.sigma.rest.menu.dto.MenuDTO;

import java.util.List;

public interface MenuRepository extends BaseMapper<Menu> {

    IPage<MenuDTO> queryMenuList(MenuQueryPageCondition condition);

    List<MenuDTO> queryMenuByParentCode(String parentCode);

    List<MenuDTO> queryMenuByRoleCode(String roleCode);
}
