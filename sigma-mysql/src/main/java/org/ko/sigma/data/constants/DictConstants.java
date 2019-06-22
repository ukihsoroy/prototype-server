package org.ko.sigma.data.constants;

import org.ko.sigma.data.bean.BasicColumnConstants;

public class DictConstants {

    public static class Columns extends BasicColumnConstants {

        /**
         * 主键
         */
        public static String ID = "id";

        /**
         * 字典编码，由系统统一生成，不可修改
         */
        public static String CODE = "code";

        /**
         * 字典类型
         */
        public static String TYPE = "type";

        /**
         * 字典编码
         */
        public static String DICT_CODE = "dict_code";

        /**
         * 字典名称
         */
        public static String DICT_NAME = "dict_name";
    }

}