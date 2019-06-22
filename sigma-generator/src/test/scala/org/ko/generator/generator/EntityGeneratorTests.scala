package org.ko.generator.generator

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource
import org.junit.{Before, Test}
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
  private val entity: EntityGenerator = null

  @Before
  def before (): Unit = {
    val dataSource = new MysqlDataSource
    dataSource.setDatabaseName("sigma_server")
    dataSource.setPort(3306)
    dataSource.setUser("root")
    dataSource.setPassword("tiger")
    entity.dataSource(dataSource)
  }

  @Test
  def generator (): Unit = {
    entity.executor("t_department")
  }

}
