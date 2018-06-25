package org.ko.generator;

import org.ko.generator.helper.GeneratorHelper;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeneratorApplication {

    public static void main(String[] args) {
        GeneratorHelper.run(GeneratorApplication.class, args);
    }
}
