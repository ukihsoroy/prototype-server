package org.ko.framework.core.utils;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class SecurityUtils {

    public final static String JWT_TOKEN_KEY = "auth-jwt";

    private final static HashFunction sha512 = Hashing.sha512();

    private final static Splitter splitter = Splitter.on('@').trimResults();

    private final static Joiner joiner = Joiner.on('@').skipNulls();

    private final static HashFunction md5 = Hashing.md5();

    /**
     * cookie中的path的值
     */
    public static final String DIR_SEPARATOR = "/";

    /**
     * 从HttpServletRequest取得token
     * @param request HttpServletRequest
     * @param key token对应的key值
     * @return token
     */
    public static String getTokenFromCookies(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (key.equalsIgnoreCase(cookie.getName())) {
                    return EscapeEncode.unescape(cookie.getValue());
                }
            }
        }
        return null;
    }

    /**
     * 将token保存到cookie中
     * @param response HttpServletResponse
     * @param key token对应的key值
     * @param value token
     * @param day cookie保存天数
     * @param domain 域名
     */
    public static void setTokenToCookie(HttpServletResponse response, String key, String value, int day, String domain) {

        value = EscapeEncode.escape(value);
        Cookie cookie = new Cookie(key, value);
        cookie.setDomain(domain);
        cookie.setPath(DIR_SEPARATOR);
        // HTTPS传递
        cookie.setSecure(false);
        // HTTP传递
        cookie.setHttpOnly(true);
        if (day > 0) {
            cookie.setMaxAge(60 * 60 * 24 * day);
        }
        response.addCookie(cookie);
    }


    /**
     * 密码加密
     *
     * @param password
     *            密码
     * @return 加密后的密码
     */
    public static String encryptPassword(String password) {
        String salt = md5.newHasher()
                .putUnencodedChars(UUID.randomUUID().toString())
                .putLong(System.currentTimeMillis()).hash().toString()
                .substring(0, 8);
        String realPassword = sha512.hashUnencodedChars(password + salt)
                .toString().substring(0, 24);
        return joiner.join(salt, realPassword);
    }

    /**
     * 密码匹配校验
     *
     * @param password
     *            传入密码 原码
     * @param encryptedPassword
     *            已有加密密码
     * @return 匹配结果
     */
    public static boolean passwordMatch(String password, String encryptedPassword) {
        Iterable<String> parts = splitter.split(encryptedPassword);
        String salt = Iterables.get(parts, 0);
        String realPassword = Iterables.get(parts, 1);
        String valPassword = sha512.hashUnencodedChars(password + salt)
                .toString().substring(0, 24);
        return Objects.equal(valPassword, realPassword);
    }

    /**
     * 生成一个新的token（sessionID）
     * @return
     */
    public static String createToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
