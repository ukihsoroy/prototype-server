package org.ko.sigma.core.service;

public interface IMailService {

    void sendCode(String name, String mail) throws Exception;
}
