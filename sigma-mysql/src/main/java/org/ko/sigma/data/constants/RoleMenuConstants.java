package org.ko.sigma.data.constants;

public class RoleMenuConstants {

    public static class Columns {

        /**
         * 主键
         */
        public static String ID = "id";

        /**
         * 角色编码，由系统统一生成，不可修改
         */
        public static String ROLE_CODE = "role_code";

        /**
         * 菜单编码，由系统统一生成，不可修改
         */
        public static String MENU_CODE = "menu_code";

        /**
         * 创建数据权限：0-否，1-是
         */
        public static String CREATE = "create";

        /**
         * 读取详情权限：0-否，1-是
         */
        public static String READ = "read";

        /**
         * 更新数据权限：0-否，1-是
         */
        public static String UPDATE = "update";

        /**
         * 删除数据权限：0-否，1-是
         */
        public static String DELETE = "delete";

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