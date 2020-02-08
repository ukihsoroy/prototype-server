package org.ko.codegen.power;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 下划线转驼峰测试类
 * @author K.O
 */
public class UnderToCamelTests {

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /** 下划线转驼峰 */
    protected static String mapUnderscoreToCamelCase(String value) {
        value = value.toLowerCase();
        Matcher matcher = linePattern.matcher(value);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    @Test public void test1() {
        String value = "t_user_info";
        String camel = mapUnderscoreToCamelCase(value);
        System.out.println(camel);
    }

}
