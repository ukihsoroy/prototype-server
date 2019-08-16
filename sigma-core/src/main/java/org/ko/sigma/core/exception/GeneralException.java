package org.ko.sigma.core.exception;

import org.ko.sigma.core.type.SystemCode;

/**
 * GeneralException
 */
public class GeneralException extends CustomRuntimeException {

    private static final long serialVersionUID = 1633213333563068730L;

    private Integer code;

    public GeneralException() {}

    public GeneralException(Throwable cause) {
        super(cause);
    }

    public GeneralException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralException(String message) {
        super(message);
    }

    public GeneralException(SystemCode systemCode) {
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
