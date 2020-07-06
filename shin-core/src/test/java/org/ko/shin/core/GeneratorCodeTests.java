package org.ko.shin.core;

import org.junit.Test;
import org.ko.shin.core.util.GeneratorCodeUtils;

public class GeneratorCodeTests {

    @Test
    public void whenGeneratorCodeSuccess() {
        for (int i = 0; i < 100; i++) {
            String code = GeneratorCodeUtils.numberCode();
            System.out.println(code);
        }
    }

}
