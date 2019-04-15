package org.ko.prototype.rest.menu.service.impl;

import org.ko.prototype.core.exception.TransactionalException;
import org.ko.prototype.core.type.SystemCode;
import org.ko.prototype.core.type.SystemConstants;
import org.ko.prototype.data.master.domain.Menu;
import org.ko.prototype.rest.menu.condition.MenuQueryListCondition;
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
    public List<Menu> queryMenuList(MenuQueryListCondition condition) {
        Example example = new Example(Menu.class);
        example.createCriteria().andEqualTo("del_status", SystemConstants.DelStatus.Available);
        return menuRepository.selectByExample(example);
    }

    @Override
    public Menu queryMenuInfo(Long id) {
        return menuRepository.selectByPrimaryKey(id);
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
