package org.ko.prototpye.core.env;


import org.ko.prototpye.core.base.BaseUser;

/**
 * 
 * UserHolder
 *
 * @author <A>calder</A>
 *
 */
public final class UserHolder {

    public static final String USER_KEY = "user";

    private static ThreadLocal<BaseUser> user = new ThreadLocal<>();

    public static void putCurrentUser(BaseUser baseUser) {
        user.set(baseUser);
    }

    public static BaseUser getCurrentUser() {
        return (BaseUser) user.get();
    }

    public static void removeUser() {
        user.remove();
    }

    public static String getUserId() {
        BaseUser baseUser = getCurrentUser();
        return baseUser != null ? baseUser.getId() : null;
    }

}
