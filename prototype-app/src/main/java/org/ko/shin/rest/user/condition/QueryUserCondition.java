package org.ko.prototype.rest.user.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.prototype.data.bean.PageCondition;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryUserCondition<User> extends PageCondition<User> {

    private String username;

    private String nickname;

    private Short gender;

    private String roleCode;

    private String mobile;

    private String email;

}

