package org.ko.prototype.rest.user.repository;

import org.ko.prototype.data.master.domain.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserRepository extends Mapper<User> {



}
