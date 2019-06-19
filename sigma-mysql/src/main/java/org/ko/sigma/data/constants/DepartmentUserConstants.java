package org.ko.sigma.data.constants;

public class DepartmentUserConstants {

    public static class Columns {

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
        public static String USER_CODE = "user_code";

        /**
         * 部门角色，t_dict表配置
         */
        public static String ROLE_CODE = "role_code";

        /**
         * 数据状态：0-删除，1-有效
         */
        public static String ENABLE = "enable";

        /**
         * 更新版本
         */
        public static String VERSION = "version";

        /**
         * 创建用户
         */
        public static String CREATE_USER = "create_user";

        /**
         * 创建时间
         */
        public static String GMT_CREATE = "gmt_create";

        /**
         * 更新用户
         */
        public static String MODIFIED_USER = "modified_user";

        /**
         * 更新时间
         */
        public static String GMT_MODIFIED = "gmt_modified";
    }

}