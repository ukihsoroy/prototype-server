package org.ko.generator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.ko.generator.generator.MybatisGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisGeneratorTests extends MybatisGenerator {

	private static final Logger log = LoggerFactory.getLogger(MyBatisGeneratorTests.class);
	
	@Test
	@Override
	public void generator() {
		buildSingleMapper("t_admin_user");
//		buildAllMappers();
	}

	private void buildSingleMapper(String... tables) {
		try {
			generateStubs(tables);
			generateDomainConstants(tables);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void buildAllMappers() {
		try {
			super.buildAllMappers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
