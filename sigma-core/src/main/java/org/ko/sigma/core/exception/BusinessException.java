package org.ko.sigma.core.exception;

import org.ko.sigma.core.type.SystemCode;

/**
 * GeneralException
 */
public class BusinessException extends CustomRuntimeException {

    private static final long serialVersionUID = 1633213333563068730L;

    private Integer code;

    public BusinessException() {}

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(SystemCode systemCode) {
        super(systemCode.getMsg());
        this.code = systemCode.getCode();
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
