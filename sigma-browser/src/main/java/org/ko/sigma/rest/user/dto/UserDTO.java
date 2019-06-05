package org.ko.sigma.rest.user.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.ko.sigma.core.bean.entity.UserEntity;

import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class UserDTO extends UserEntity {

    private Long id;

    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Past(message = "生日必须是过去的时间")
    private Date birthday;

}
