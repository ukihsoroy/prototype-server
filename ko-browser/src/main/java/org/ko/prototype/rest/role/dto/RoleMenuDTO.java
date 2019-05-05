package org.ko.prototype.rest.role.dto;

import org.ko.prototype.data.master.domain.Menu;
import org.ko.prototype.data.master.domain.Role;
import org.ko.prototype.data.master.domain.RoleMenu;

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
