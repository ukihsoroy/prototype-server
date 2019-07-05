package org.ko.sigma.data.constants;

import org.ko.sigma.data.bean.BasicColumnConstants;

public class UserRoleConstants {

    public static class Columns extends BasicColumnConstants {

        /**
         * 主键
         */
        public static String ID = "id";

        /**
         * 用户编码（UUID），由系统统一生成，不可修改
         */
        public static String USER_ID = "user_id";

        /**
         * 角色编码，由系统统一生成，不可修改
         */
        public static String ROLE_ID = "role_id";

        /**
         * 数据状态：0-有效，1-删除
         */
        public static String DISABLE = "disable";
    }

}