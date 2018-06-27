package org.ko.prototype.core.type;

/**
 * 系统错误码
 */
public enum SystemCode {

    SUCCESS("200", "Request success."),
    SYSTEM_ERROR("500", "System error.")
    ;

    private String code;

    private String msg;

    SystemCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
