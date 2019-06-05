package org.ko.sigma.rest.user.service.impl;

import org.ko.sigma.core.bean.entity.UserEntity;
import org.ko.sigma.core.exception.TransactionalException;
import org.ko.sigma.core.type.SystemConstants;
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
        Example e = new Example(UserEntity.class);
        e.createCriteria()
                .andEqualTo("username", username)
                .andEqualTo("availableStatus", "1");
        return userRepository.selectOneByExample(e);
    }


    @Override
    public List<UserEntity> queryUserList(UserQueryListCondition condition) {
        Example e = new Example(UserEntity.class);
        e.createCriteria()
                .andEqualTo("availableStatus", "1");
        return userRepository.selectByExample(e);
    }

    @Override
    public UserEntity queryUserInfo(Long id) {
        return userRepository.selectByPrimaryKey(id);
    }

    @Override
    public Long createUser(UserEntity userEntity) {
        userEntity.setAvailableStatus(SystemConstants.AvailableStatus.Available);
        if (userRepository.insert(userEntity) == 0) {
            throw new TransactionalException(INSERT_ERROR);
        }
        return userEntity.getId();
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity userEntity) {
        userEntity.setId(id);
        if (userRepository.updateByPrimaryKeySelective(userEntity) == 0) {
            throw new TransactionalException(UPDATE_ERROR);
        }
        return userEntity;
    }

    @Override
    public Long removeUser(Long id) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setAvailableStatus(SystemConstants.AvailableStatus.Deleted);
        if (userRepository.updateByPrimaryKeySelective(userEntity) == 0) {
            throw new TransactionalException(DELETE_ERROR);
        }
        return id;
    }
}
