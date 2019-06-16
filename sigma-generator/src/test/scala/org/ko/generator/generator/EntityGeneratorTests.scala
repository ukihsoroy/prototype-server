package org.ko.generator.generator

import org.junit.Test
import org.junit.runner.RunWith
import org.ko.generator.core.EntityGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@Test
@SpringBootTest
@RunWith(classOf[SpringRunner])
class EntityGeneratorTests {

  @Autowired
  private var entity: EntityGenerator = _

  @Test
  def generator (): Unit = {
    entity.executor("t_user")
  }

}
