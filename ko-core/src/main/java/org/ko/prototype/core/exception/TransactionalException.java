package org.ko.prototype.core.exception;

import org.ko.prototype.core.type.SystemCode;

public class TransactionalException extends GeneralException {

    public TransactionalException(SystemCode code) {
        super(code);
    }

}
