package org.ko.prototype.data.constants;

import org.ko.prototype.data.bean.BasicColumnConstants;

public class SmsLogConstants {

    public static class Columns extends BasicColumnConstants {

        /**
         * 主键ID
         */
        public static String ID = "id";

        /**
         * 用户ID
         */
        public static String USER_ID = "user_id";

        /**
         * 发送短信类型
         */
        public static String TYPE = "type";

        /**
         * 短信验证码
         */
        public static String SMS_CODE = "sms_code";

        /**
         * 失效时间，单位秒
         */
        public static String EXPIRE_IN = "expire_in";

        /**
         * 逻辑删除状态
         */
        public static String DISABLE = "disable";
    }

}