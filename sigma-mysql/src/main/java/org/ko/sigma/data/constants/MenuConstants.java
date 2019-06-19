package org.ko.sigma.data.constants;

public class MenuConstants {

    public static class Columns {

        /**
         * 主键
         */
        public static String ID = "id";

        /**
         * 菜单编码，由系统统一生成，不可修改
         */
        public static String CODE = "code";

        /**
         * 菜单名称
         */
        public static String NAME = "name";

        /**
         * 菜单路由规则索引
         */
        public static String PATH = "path";

        /**
         * 命名视图组件
         */
        public static String COMPONENT = "component";

        /**
         * 路由重定向
         */
        public static String REDIRECT = "redirect";

        /**
         * 菜单元数据。 name: 路由名称，icon: 路由图标
         */
        public static String META_JSON = "meta_json";

        /**
         * 上级菜单编码，由系统统一生成，不可修改
         */
        public static String PARENT_CODE = "parent_code";

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