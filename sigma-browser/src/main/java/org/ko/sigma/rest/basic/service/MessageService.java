package org.ko.sigma.rest.basic.service;

public interface MessageService {

    void send(String address, String messageType) throws Exception;
}
