package org.ko.sigma.core.util;

import org.ko.sigma.core.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

import static org.ko.sigma.core.type.SystemCode.SYSTEM_ERROR;


public final class BASE64 {

    private static final Logger logger = LoggerFactory.getLogger(BASE64.class);

    private static String[] REGEXS = {"\n", "\r"};

    /**
     * <p>BASE64解密</p>
     * @param key 解密前
     * @return 解密后#Array
     */
    public static byte[] decryptBASE64(String key) {
        try {
            return (new BASE64Decoder()).decodeBuffer(key);
        } catch (IOException e) {
            logger.error("org.ko.prototype.core.utils.BASE64#decryptBASE64 exception: {}", e.getMessage());
            throw new BusinessException(SYSTEM_ERROR);
        }
    }

    /**
     * <p>BASE64加密</p>
     * @param key 加密前
     * @return 加密后
     */
    public static String encryptBASE64(byte[] key) {
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encodeBuffer(key);
        String empty = "";
        for (String regex : REGEXS) {
            encode = encode.replaceAll(regex, empty);
        }
        return encode;
    }

    /**
     * <p>BASE64解密返回字符串</p>
     * @param key 解密前
     * @return 解密后#String
     */
    public static String decryptBASE64String(String key) {
        return new String(decryptBASE64(key));
    }

    private BASE64(){}
}
