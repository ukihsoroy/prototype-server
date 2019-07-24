package org.ko.sigma.rest.user.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.sigma.data.bean.PageCondition;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryUserPageCondition<User> extends PageCondition<User> {

    private String username;

    private String nickname;

    private Short gender;

    private String roleCode;

    private String mobile;

    private String email;

}

