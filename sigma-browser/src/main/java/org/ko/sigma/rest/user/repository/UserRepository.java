package org.ko.sigma.rest.user.repository;

import org.ko.sigma.data.master.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserRepository extends Mapper<User> {



}
