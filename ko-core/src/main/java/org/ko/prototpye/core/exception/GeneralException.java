package org.ko.prototpye.core.exception;

/**
 * GeneralException
 */
public class GeneralException extends CustomRuntimeException {

    private static final long serialVersionUID = 1633213333563068730L;

    public GeneralException() {
    }

    public GeneralException(Throwable cause) {
        super(cause);
    }

    public GeneralException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralException(String message) {
        super(message);
    }

}
