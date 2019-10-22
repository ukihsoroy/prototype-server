package org.ko.sigma.rest.basic.service;

public interface MailService {

    void sendCode(String name, String mail) throws Exception;
}
