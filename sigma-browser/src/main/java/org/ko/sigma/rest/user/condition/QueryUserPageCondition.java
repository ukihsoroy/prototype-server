package org.ko.sigma.rest.user.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.sigma.data.bean.PageCondition;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryUserPageCondition<UserEntity> extends PageCondition<UserEntity> {

    private String mobile;


}

