package org.ko.generator.jdbc

import java.sql.ResultSet

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource
import org.apache.commons.lang3.StringUtils
import org.junit.{Before, Test}
import org.ko.generator.bean.entity.Column
import org.ko.generator.constants.SQLConstants
import org.springframework.jdbc.core.{JdbcTemplate, RowMapper}

import collection.JavaConverters._
import org.ko.generator.constants.SchemaColumnNameConstants._
import org.ko.generator.util.ConverterHelper._
import org.ko.generator.util.ConverterSQLTypeHandler

@Test
class JDBCTests {

  val jDBCTemplate = new JdbcTemplate()

  @Before
  def before (): Unit = {
    val dataSource = new MysqlDataSource
    dataSource.setDatabaseName("sigma_server")
    dataSource.setPort(3306)
    dataSource.setUser("root")
    dataSource.setPassword("tiger")
    jDBCTemplate.setDataSource(dataSource)
  }

  @Test
  def whenQueryTableSuccess (): Unit = {
    val tables = jDBCTemplate.queryForList(SQLConstants.INFORMATION_SCHEMA_TABLES, classOf[String], "sigma_server").asScala
    tables.foreach(println)
  }

  @Test
  def whenQueryColumnSuccess (): Unit = {
    val columns = jDBCTemplate.query(SQLConstants.INFORMATION_SCHEMA_COLUMNS, new RowMapper[Column]() {
      override def mapRow(rs: ResultSet, i: Int): Column= {
        val columnName = rs.getString(COLUMN_NAME)
        println("###columnName: " + columnName)
        val columnType = rs.getString(DATA_TYPE).toLowerCase()
        println("###columnType: " + columnType)
        val charLength = int(rs.getString(CHARACTER_MAXIMUM_LENGTH))
        println("###charLength: " + charLength)
        val precision = int(rs.getString(NUMERIC_PRECISION))
        println("###precision: " + precision)
        val scale = int(rs.getString(NUMERIC_SCALE))
        println("###scale: " + scale)
        var len = charLength + precision + scale
        println("###len: " + len)
        if (scale != 0) len = len + 1
        println("###len: " + len)
        Column(
          columnName = columnName,
          propertyName = mapUnderscoreToCamelCase(columnName),
          columnType = columnType,
          propertyType = ConverterSQLTypeHandler.format(columnType),
          primaryKey = PRI.equalsIgnoreCase(rs.getString(COLUMN_KEY)),
          length = len,
          common = StringUtils.trimToEmpty(rs.getString(COLUMN_COMMENT))
        )
      }
    }, "t_user", "sigma_server")

    println(columns.size())
  }

  @Test
  def whenToUpperSuccess (): Unit = {
    val s = mapUnderscoreToCamelCase("gmt_create")
    println(s)
  }

}
