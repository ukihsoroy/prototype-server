package org.ko.shin.rest.basic.service;

public interface IdentifyingCodeService {

    void sendCode(String address, String messageType) throws Exception;

    void checkCode(String address, String messageType, String code) throws Exception;
}
