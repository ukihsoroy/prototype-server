package org.ko.sigma.rest.menu.dto;

import org.ko.sigma.data.entity.Menu;

import java.util.List;

public class MenuDTO extends Menu {

    private List<MenuDTO> menuDTOS;

    public List<MenuDTO> getMenuDTOS() {
        return menuDTOS;
    }

    public void setMenuDTOS(List<MenuDTO> menuDTOS) {
        this.menuDTOS = menuDTOS;
    }
}
