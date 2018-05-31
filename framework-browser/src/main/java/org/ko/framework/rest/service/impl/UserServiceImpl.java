package org.ko.framework.rest.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.ko.framework.data.master.domain.User;
import org.ko.framework.rest.condition.UserQueryCondition;
import org.ko.framework.rest.dto.UserDTO;
import org.ko.framework.rest.repository.UserRepository;
import org.ko.framework.rest.service.UserService;
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

    private static final Logger _LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查找用户信息
        return null;
    }


    @Override
    public List<UserDTO> queryUserList(UserQueryCondition condition) {
        Example e = new Example(User.class);
        List<User> users = userRepository.selectByExample(e);
        if (CollectionUtils.isNotEmpty(users)) {
            return users.stream().map(u -> {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(u, userDTO);
                return userDTO;
            }).collect(Collectors.toList());
        }
        return null;
    }
}
