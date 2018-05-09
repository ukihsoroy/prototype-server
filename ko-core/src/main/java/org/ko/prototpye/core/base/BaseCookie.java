package org.ko.prototpye.core.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * BaseCookie
 *
 * @author <A>calder</A>
 *
 */
@Getter
@Setter
@ToString
public class BaseCookie {

    private Map<Object, Object> cookies;

    public void setCookies(Cookie[] cookies) {
        if (this.cookies == null) {
            this.cookies = new HashMap<Object, Object>();
        } else {
            this.cookies.clear();
        }
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                String key = cookies[i].getName();
                String value = cookies[i].getValue();
                this.cookies.put(key, value);
            }
        }
    }

    public Map<Object, Object> getCookies() {
        if (this.cookies == null) {
            this.cookies = new HashMap<Object, Object>();
        }
        return this.cookies;
    }

    public void addCookie(Object key, Object value) {
        if (this.cookies == null) {
            this.cookies = new HashMap<Object, Object>();
        }
        if (key != null && value != null) {
            this.cookies.put(key, value);
        }
    }

}
