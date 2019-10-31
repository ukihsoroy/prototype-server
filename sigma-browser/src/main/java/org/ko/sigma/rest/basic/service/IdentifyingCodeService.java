package org.ko.sigma.rest.basic.service;

public interface IdentifyingCodeService {

    void send(String address, String messageType) throws Exception;
}
