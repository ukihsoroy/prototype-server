package org.ko.framework.rest.repository;

import org.ko.framework.data.master.domain.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserRepository extends Mapper<User> {

}
