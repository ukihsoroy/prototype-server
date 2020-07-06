package org.ko.shin.data.constants;

import org.ko.shin.data.bean.BasicColumnConstants;

public class DepartmentUserConstants {

    public static class Columns extends BasicColumnConstants {

        /**
         * 主键
         */
        public static String ID = "id";

        /**
         * 部门编码，由系统统一生成，不可修改
         */
        public static String DEPARTMENT_CODE = "department_code";

        /**
         * 用户编码（UUID），由系统统一生成，不可修改
         */
        public static String USER_ID = "user_id";
    }

}