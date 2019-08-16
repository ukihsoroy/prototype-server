package org.ko.sigma.core.exception;

public class UniqueException extends GeneralException {
    public UniqueException(String message) {
        super(message);
        super.setCode(503);
    }
}
