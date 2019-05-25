package org.ko.sigma.rest.menu.service.impl;

import org.ko.sigma.core.exception.TransactionalException;
import org.ko.sigma.core.type.SystemCode;
import org.ko.sigma.core.type.SystemConstants;
import org.ko.sigma.data.master.entity.Menu;
import org.ko.sigma.rest.menu.condition.MenuQueryListCondition;
import org.ko.sigma.rest.menu.dto.MenuDTO;
import org.ko.sigma.rest.menu.repository.MenuRepository;
import org.ko.sigma.rest.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class MenuServiceImpl implements MenuService {

    @Autowired private MenuRepository menuRepository;

    @Override
    public List<MenuDTO> queryMenuList(MenuQueryListCondition condition) {
        return menuRepository.queryMenuList(condition);
    }

    @Override
    public MenuDTO queryMenuInfo(Long id) {
        return menuRepository.queryMenuById(id);
    }

    @Override
    public List<MenuDTO> queryMenuByRoleId(Long roleId) {
        return menuRepository.queryMenuByRoleId(roleId);
    }

    @Override
    public List<MenuDTO> queryMenuByParentId(Long id) {
        return menuRepository.queryMenuByParentId(id);
    }

    @Override
    public Long createMenu(Menu menu) {
        menu.setAvailableStatus(SystemConstants.AvailableStatus.Available);
        if (menuRepository.insertSelective(menu) == 0) {
            throw new TransactionalException(SystemCode.INSERT_ERROR);
        }
        return menu.getId();
    }

    @Override
    public Menu updateMenu(Long id, Menu menu) {
        menu.setId(id);
        if (menuRepository.updateByPrimaryKeySelective(menu) == 0) {
            throw new TransactionalException(SystemCode.UPDATE_ERROR);
        }
        return menu;
    }

    @Override
    public Long deleteMenu(Long id) {
        Menu menu = new Menu();
        menu.setId(id);
        menu.setAvailableStatus(SystemConstants.AvailableStatus.Deleted);
        if (menuRepository.updateByPrimaryKeySelective(menu) == 0) {
            throw new TransactionalException(SystemCode.DELETE_ERROR);
        }
        return id;
    }
}
