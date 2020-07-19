package org.ko.prototype.core;

import org.junit.Test;
import org.ko.prototype.core.util.GeneratorCodeUtils;

public class GeneratorCodeTests {

    @Test
    public void whenGeneratorCodeSuccess() {
        for (int i = 0; i < 100; i++) {
            String code = GeneratorCodeUtils.numberCode();
            System.out.println(code);
        }
    }

}
