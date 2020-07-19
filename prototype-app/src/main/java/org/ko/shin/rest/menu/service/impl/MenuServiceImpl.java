package org.ko.prototype.rest.menu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ko.prototype.core.exception.BusinessException;
import org.ko.prototype.core.constant.SystemCode;
import org.ko.prototype.data.entity.Menu;
import org.ko.prototype.rest.menu.condition.QueryMenuCondition;
import org.ko.prototype.rest.menu.dto.MenuDTO;
import org.ko.prototype.rest.menu.repository.MenuRepository;
import org.ko.prototype.rest.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class MenuServiceImpl extends ServiceImpl<MenuRepository, Menu> implements MenuService {

    @Autowired private MenuRepository menuRepository;

    @Override
    public IPage<MenuDTO> queryMenuList(QueryMenuCondition condition) {
        return menuRepository.queryMenuList(condition);
    }

    @Override
    public Menu queryMenuInfo(Long id) {
        return menuRepository.selectById(id);
    }

    @Override
    public List<MenuDTO> queryMenuByRoleCode(String roleCode) {

        // 查询该权限下的全部菜单
        List<MenuDTO> menuDTOS = menuRepository.queryMenuByRoleCode(roleCode);

        // 取出一级
        List<MenuDTO> parentMenus = menuDTOS.stream().filter(menuDTO -> menuDTO.getParentId() == null)
                .collect(Collectors.toList());

        // 对菜单按照parent_id分组
        Map<Long, List<MenuDTO>> childrenMenuMap = menuDTOS.stream().filter(menuDTO -> menuDTO.getParentId() != null)
                .collect(Collectors.groupingBy(MenuDTO::getParentId));

        // 装填菜单
        parentMenus.forEach(parentMenu -> deepPutChildrenMenu(parentMenu, childrenMenuMap));

        return parentMenus;
    }

    /**
     * 递归添加子菜单
     * @param parentMenu 父菜单
     * @param childrenMenuMap 子菜单结合
     */
    private void deepPutChildrenMenu(MenuDTO parentMenu, Map<Long, List<MenuDTO>> childrenMenuMap) {
        Long parentId = parentMenu.getId();
        if (childrenMenuMap.containsKey(parentId)) {
            List<MenuDTO> children = childrenMenuMap.get(parentId);
            parentMenu.setChildren(children);
            children.forEach(menuDTO -> deepPutChildrenMenu(menuDTO, childrenMenuMap));
        }
    }

    @Override
    public List<MenuDTO> queryMenuByParentId(Long parentId) {
        return menuRepository.queryMenuByParentId(parentId);
    }

    @Override
    public Long createMenu(Menu menu) {
        if (menuRepository.insert(menu) == 0) {
            throw new BusinessException(SystemCode.UNKNOWN_ERROR);
        }
        return menu.getId();
    }

    @Override
    public Menu updateMenu(Long id, Menu menu) {
        menu.setId(id);
        if (menuRepository.updateById(menu) == 0) {
            throw new BusinessException(SystemCode.UNKNOWN_ERROR);
        }
        return menu;
    }

    @Override
    public Long deleteMenu(Long id) {
        if (menuRepository.deleteById(id) == 0) {
            throw new BusinessException(SystemCode.UNKNOWN_ERROR);
        }
        return id;
    }

}
