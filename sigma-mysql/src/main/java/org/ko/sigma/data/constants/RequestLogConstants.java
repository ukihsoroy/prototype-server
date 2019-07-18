package org.ko.sigma.data.constants;

import org.ko.sigma.data.bean.BasicColumnConstants;

public class RequestLogConstants {

    public static class Columns extends BasicColumnConstants {

        /**
         * 主键ID，使用UUID
         */
        public static String ID = "id";

        /**
         * 请求用户ID
         */
        public static String USER_ID = "user_id";

        /**
         * 用户ip信息
         */
        public static String IP_ADDR = "ip_addr";

        /**
         * 请求时间
         */
        public static String REQUEST_TIME = "request_time";

        /**
         * 请求url
         */
        public static String REQUEST_LINK = "request_link";

        /**
         * 创建用户ID
         */
        public static String CREATE_USER_ID = "create_user_id";

        /**
         * 创建时间
         */
        public static String CREATE_DATE = "create_date";

        /**
         * 更新用户ID
         */
        public static String UPDATE_USER_ID = "update_user_id";

        /**
         * 更新时间
         */
        public static String UPDATE_DATE = "update_date";
    }

}