package org.ko.sigma.rest.user.repository;

import org.ko.sigma.core.bean.entity.UserEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserRepository extends Mapper<UserEntity> {



}
