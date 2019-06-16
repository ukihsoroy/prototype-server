package org.ko.generator.generator

import org.junit.Test
import org.ko.generator.core.EntityGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@Test
@SpringBootTest
class EntityGeneratorTests {

  @Autowired
  private val entity: EntityGenerator = null

  @Test
  def generator (): Unit = {
    entity.executor("t_user")
  }

}
