package org.ko.codegen.util;

import java.util.HashMap;
import java.util.Map;

/**
 * description: mysql数据类型与java转换 <br>
 * date: 1/31/2020 19:52 <br>
 *
 * @author zhiyuan.shen <br>
 * @version 1.0 <br>
 */
public final class ConverterSQLTypeHandler {

    private static final Map<String, String> container = new HashMap<>();

    static {
        container.put("varchar", "String");
        container.put("char", "String");
        container.put("text", "String");
        container.put("int", "int");
        container.put("tinyint", "byte");
        container.put("smallint", "short");
        container.put("mediumint", "short");
        container.put("bigint", "Long");
        container.put("float", "float");
        container.put("double", "double");
        container.put("decimal", "java.math.BigDecimal");
        container.put("date", "java.util.Date");
        container.put("datetime", "java.util.Date");
        container.put("timestamp", "java.util.Date");
        container.put("json", "String");
    }

    public static String format(String key) {
        return container.get(key);
    }

}
