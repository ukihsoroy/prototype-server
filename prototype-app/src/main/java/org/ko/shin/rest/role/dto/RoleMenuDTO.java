package org.ko.prototype.rest.role.dto;

import org.ko.prototype.data.entity.Menu;
import org.ko.prototype.data.entity.Role;
import org.ko.prototype.data.entity.RoleMenu;

import java.util.List;

public class RoleMenuDTO extends RoleMenu {

    private Role role;

    private List<Menu> menus;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
