package org.ko.shin.rest.role.dto;

import org.ko.shin.data.entity.Role;
import org.springframework.security.core.GrantedAuthority;

public class RoleDTO extends Role implements GrantedAuthority {



    @Override
    public String getAuthority() {
        return getCode();
    }
}
