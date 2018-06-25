package org.ko.prototype.rest.user.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.ko.prototype.data.master.domain.AdminUser;
import org.ko.prototype.rest.user.condition.AdminUserQueryCondition;
import org.ko.prototype.rest.user.dto.AdminUserDTO;
import org.ko.prototype.rest.user.repository.AdminUserRepository;
import org.ko.prototype.rest.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired private AdminUserRepository adminUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查找用户信息
        return null;
    }


    @Override
    public List<AdminUserDTO> queryUserList(AdminUserQueryCondition condition) {
        Example e = new Example(AdminUser.class);
        e.createCriteria()
                .andEqualTo("delete_status", "1");
        List<AdminUser> users = adminUserRepository.selectByExample(e);
        if (CollectionUtils.isNotEmpty(users)) {
            return users.stream().map(u -> {
                AdminUserDTO adminUserDTO = new AdminUserDTO();
                BeanUtils.copyProperties(u, adminUserDTO);
                return adminUserDTO;
            }).collect(Collectors.toList());
        }
        return null;
    }
}
