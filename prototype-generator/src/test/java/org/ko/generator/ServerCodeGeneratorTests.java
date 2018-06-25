package org.ko.generator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.generator.generator.ServerCodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerCodeGeneratorTests extends ServerCodeGenerator {

    private static final Logger log = LoggerFactory.getLogger(ServerCodeGeneratorTests.class);

    @Test
    @Override
    public void generator() {
		buildSingleRepository("art_link");
    }

    private void buildSingleRepository(String... tables) {
        try {
            generateStubs(tables);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
