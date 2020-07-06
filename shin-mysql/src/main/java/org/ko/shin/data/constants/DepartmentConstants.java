package org.ko.shin.data.constants;

import org.ko.shin.data.bean.BasicColumnConstants;

public class DepartmentConstants {

    public static class Columns extends BasicColumnConstants {

        /**
         * 主键
         */
        public static String ID = "id";

        /**
         * 部门编码，由系统统一生成，不可修改
         */
        public static String CODE = "code";

        /**
         * 部门名称
         */
        public static String NAME = "name";

        /**
         * 父级部门编码
         */
        public static String PARENT_CODE = "parent_code";
    }

}