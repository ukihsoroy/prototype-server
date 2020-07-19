package org.ko.prototype.rest.role.dto;

import org.ko.prototype.data.entity.Role;
import org.springframework.security.core.GrantedAuthority;

public class RoleDTO extends Role implements GrantedAuthority {



    @Override
    public String getAuthority() {
        return getCode();
    }
}
