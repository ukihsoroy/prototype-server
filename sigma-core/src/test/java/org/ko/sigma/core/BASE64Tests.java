package org.ko.sigma.core;

import org.junit.Test;
import org.ko.sigma.core.util.BASE64;

public class BASE64Tests {

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
        try {
            String result = new String(BASE64.decryptBASE64("MTUzMDcxNDIxNDgzMy50eHQ=\r\n"));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
