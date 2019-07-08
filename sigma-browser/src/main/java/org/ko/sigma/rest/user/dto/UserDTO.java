package org.ko.sigma.rest.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.sigma.data.entity.User;
import org.ko.sigma.rest.role.dto.RoleDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends User implements UserDetails {

    private List<RoleDTO> roleDTOS;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleDTOS;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getDisable() == 0;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }


}
