package org.ko.sigma.rest.user.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.ko.sigma.data.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@TableName("t_user")
public class UserEntity extends User implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    public boolean isAccountNonExpired() {
        return false;
    }

    @JsonIgnore
    public boolean isAccountNonLocked() {
        return false;
    }

    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @JsonIgnore
    public boolean isEnabled() {
        return this.getDisable() == 0;
    }
}