package org.ko.prototype.rest.menu.service.impl;

import org.ko.prototype.core.exception.TransactionalException;
import org.ko.prototype.core.type.SystemCode;
import org.ko.prototype.core.type.SystemConstants;
import org.ko.prototype.data.master.domain.Menu;
import org.ko.prototype.rest.menu.condition.MenuQueryListCondition;
import org.ko.prototype.rest.menu.dto.MenuDTO;
import org.ko.prototype.rest.menu.repository.MenuRepository;
import org.ko.prototype.rest.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

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
    public List<MenuDTO> queryMenuByParentId(Long id) {
        return menuRepository.queryMenuByParentId(id);
    }

    @Override
    public Long createMenu(Menu menu) {
        menu.setDelStatus(SystemConstants.DelStatus.Available);
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
        menu.setDelStatus(SystemConstants.DelStatus.Deleted);
        if (menuRepository.updateByPrimaryKeySelective(menu) == 0) {
            throw new TransactionalException(SystemCode.DELETE_ERROR);
        }
        return id;
    }
}
