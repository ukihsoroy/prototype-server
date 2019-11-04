package org.ko.sigma.rest.menu.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.ko.sigma.data.entity.Menu;
import org.ko.sigma.rest.menu.condition.QueryMenuCondition;
import org.ko.sigma.rest.menu.dto.MenuDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends BaseMapper<Menu> {

    IPage<MenuDTO> queryMenuList(QueryMenuCondition condition);

    List<MenuDTO> queryMenuByParentId(Long parentId);

    List<MenuDTO> queryMenuByRoleCode(String roleCode);
}
