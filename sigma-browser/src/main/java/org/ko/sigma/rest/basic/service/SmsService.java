package org.ko.sigma.rest.basic.service;

public interface SmsService {

    void sendCode(String mobile, Short type);
}
