package org.ko.sigma.rest.user.service.impl;

import org.ko.sigma.core.exception.TransactionalException;
import org.ko.sigma.core.type.SystemConstants;
import org.ko.sigma.data.master.entity.User;
import org.ko.sigma.rest.user.condition.UserQueryListCondition;
import org.ko.sigma.rest.user.repository.UserRepository;
import org.ko.sigma.rest.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

import static org.ko.sigma.core.type.SystemCode.*;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查找用户信息
        return null;
    }


    @Override
    public List<User> queryUserList(UserQueryListCondition condition) {
        Example e = new Example(User.class);
        e.createCriteria()
                .andEqualTo("availableStatus", "1");
        return userRepository.selectByExample(e);
    }

    @Override
    public User queryUserInfo(Long id) {
        return userRepository.selectByPrimaryKey(id);
    }

    @Override
    public Long createUser(User user) {
        user.setAvailableStatus(SystemConstants.AvailableStatus.Available);
        if (userRepository.insert(user) == 0) {
            throw new TransactionalException(INSERT_ERROR);
        }
        return user.getId();
    }

    @Override
    public User updateUser(Long id, User user) {
        user.setId(id);
        if (userRepository.updateByPrimaryKeySelective(user) == 0) {
            throw new TransactionalException(UPDATE_ERROR);
        }
        return user;
    }

    @Override
    public Long removeUser(Long id) {
        User user = new User();
        user.setId(id);
        user.setAvailableStatus(SystemConstants.AvailableStatus.Deleted);
        if (userRepository.updateByPrimaryKeySelective(user) == 0) {
            throw new TransactionalException(DELETE_ERROR);
        }
        return id;
    }
}
