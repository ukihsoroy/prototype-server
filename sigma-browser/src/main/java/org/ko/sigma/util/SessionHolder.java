package org.ko.sigma.util;

import org.ko.sigma.rest.user.dto.UserDTO;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SessionHolder {

    public static UserDTO loginUser () {
        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private SessionHolder () {}

}
