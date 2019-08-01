package org.ko.generator.generator

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource
import org.junit.{Before, Test}
import org.junit.runner.RunWith
import org.ko.generator.core.{ConditionGenerator, DTOGenerator, EntityGenerator, RepositoryGenerator}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@Test
@SpringBootTest
@RunWith(classOf[SpringRunner])
class GeneratorTests {

  @Autowired
  private val entity: EntityGenerator = null

  @Autowired
  private val repository: RepositoryGenerator = null

  @Autowired
  private val condition: ConditionGenerator = null

  @Autowired
  private val dto: DTOGenerator = null

  @Before
  def before (): Unit = {
    val dataSource = new MysqlDataSource
    dataSource.setDatabaseName("sigma_server")
    dataSource.setPort(3306)
    dataSource.setUser("root")
    dataSource.setPassword("tiger")
    entity.dataSource(dataSource)
    repository.dataSource(dataSource)
    condition.dataSource (dataSource)
    dto.dataSource(dataSource)
  }

  @Test
  def generatorEntity (): Unit = {
    entity.executor("t_user", "t_menu", "t_department", "t_department_user", "t_dict", "t_request_log", "t_role", "t_role_menu", "t_user_role")
  }

  @Test
  def generatorRepository (): Unit = {
    repository.executor("t_department")
  }

  @Test
  def generatorCondition (): Unit = {
    condition.executor("t_department")
  }

  @Test
  def generatorDTO (): Unit = {
    dto.executor("t_department")
  }

}
