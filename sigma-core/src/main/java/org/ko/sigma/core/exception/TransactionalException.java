package org.ko.sigma.core.exception;

import org.ko.sigma.core.type.SystemCode;

public class TransactionalException extends GeneralException {

    public TransactionalException(SystemCode code) {
        super(code);
    }

    public TransactionalException(String message) {
        super(message);
    }

}
