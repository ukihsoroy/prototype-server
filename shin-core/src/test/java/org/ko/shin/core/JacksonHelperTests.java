package org.ko.shin.core;

import org.junit.Test;
import org.ko.shin.core.util.JacksonHelper;

import java.util.HashMap;
import java.util.Map;

public class JacksonHelperTests {

    @Test public void whenMap2JsonSuccess() {
        Map<String, String> params = new HashMap<>();
        params.put("code", "123456");

        String json = JacksonHelper.obj2String(params);

        System.out.println(json);
    }
}
