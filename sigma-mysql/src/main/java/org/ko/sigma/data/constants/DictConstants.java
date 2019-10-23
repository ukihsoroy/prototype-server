package org.ko.sigma.data.constants;

import org.ko.sigma.data.bean.BasicColumnConstants;

public class DictConstants {

    public static class Columns extends BasicColumnConstants {

        /**
         * 主键
         */
        public static String ID = "id";

        /**
         * 编码
         */
        public static String CODE = "code";

        /**
         * 字典值
         */
        public static String VALUE = "value";

        /**
         * 类型
         */
        public static String TYPE = "type";

        /**
         * 描述
         */
        public static String DESC = "desc";

        /**
         * 数据状态：0-有效，1-删除
         */
        public static String DISABLE = "disable";
    }

}