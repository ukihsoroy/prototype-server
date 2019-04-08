package org.ko.prototype.rest.role.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ko.prototype.core.support.Response;
import org.ko.prototype.data.master.domain.Role;
import org.ko.prototype.data.master.domain.User;
import org.ko.prototype.rest.role.dto.RoleDTO;
import org.ko.prototype.rest.role.service.RoleService;
import org.ko.prototype.rest.user.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "权限接口")
@RestController
@RequestMapping("role")
@Validated
public class RoleController {

    @Autowired private RoleService roleService;

    @PostMapping
    @ApiOperation("新增权限")
    public Response<Long> createRole (
            @ApiParam("权限传输对象实体") @RequestBody RoleDTO roleDTO) {
        Long roleId = roleService.createRole(mapRole(roleDTO));;
        return new Response<>(roleId);
    }

    /**
     * Role mapTo RoleDTO
     * @param role
     * @return roleDTO
     */
    private RoleDTO mapRole (Role role) {
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(role, roleDTO);
        return roleDTO;
    }

    /**
     * RoleDTO mapTo Role
     * @param roleDTO
     * @return
     */
    private Role mapRole (RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);
        return role;
    }

}
