package org.ko.generator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.generator.generator.BasicGenerator;
import org.ko.generator.generator.MybatisGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicGeneratorTests extends BasicGenerator {

	private static final Logger log = LoggerFactory.getLogger(BasicGeneratorTests.class);
	
	@Test
	@Override
	public void generator() {
		generatorEntity();
	}

}
