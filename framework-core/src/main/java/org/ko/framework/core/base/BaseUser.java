package org.ko.framework.core.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * BaseUser
 *
 * @author <A>Calder</A>
 *
 */
@Getter
@Setter
@ToString
public class BaseUser extends BaseModel {

    private static final long serialVersionUID = 3526851410080426485L;

    // 用户名
    private String userName;

    // 真实姓名
    private String nickName;

    // 身份证
    private String idCard;

    // 手机号码
    private String mobile;

    // 邮箱
    private String email;

    // 用户头像
    private String avatar;

    // 状态
    private int status;

    // 是否为超级用户
    private boolean isSuper;

    // 用户角色
    private List<String> roles;

    //是否第一次登录
    private Integer isFirstLogin;

    private String createBy;

    private String updateBy;

    // KEY:Authority URI VALUE:Authority JSON
    private Map<String, String> acl = new HashMap<>();

    public void addAcl(String uri, String authJson) {
        if (uri != null && authJson != null) {
            this.acl.put(uri, authJson);
        }
    }

    public void setAcl(Map<String, String> acl) {
        if (acl == null) {
            this.acl.clear();
            return;
        }
        this.acl = acl;
    }

    public boolean hasAcl(String acl) {
        return this.acl.containsKey(acl);
    }

    public void clearAcl() {
        this.acl.clear();
    }
}
