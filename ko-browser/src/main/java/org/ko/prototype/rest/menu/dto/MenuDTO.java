package org.ko.prototype.rest.menu.dto;

import org.ko.prototype.data.master.domain.Menu;

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
