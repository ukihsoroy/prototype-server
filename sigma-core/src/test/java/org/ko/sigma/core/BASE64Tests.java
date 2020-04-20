package org.ko.sigma.core;

import org.junit.Test;
import org.ko.sigma.core.util.BASE64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BASE64Tests {

    private Logger logger = LoggerFactory.getLogger(BASE64Tests.class);

    @Test
    public void whenEncodeSuccess () {
        try {
            String result = BASE64.encryptBASE64("1530631729663.txt".getBytes());
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDecodeSuccess () {
        //1563107275127.jpg
        //1563107275127.jpg
        //1563107275127.jpg
        try {
            String result = new String(BASE64.decryptBASE64("MTU2MzEwNzI3NTEyNy5qcG="));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
