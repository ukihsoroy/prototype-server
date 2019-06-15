package org.ko.generator.core

import java.sql.ResultSet

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource
import javax.sql.DataSource
import org.apache.commons.lang3.StringUtils
import org.ko.generator.bean.entity.Column
import org.ko.generator.constants.SQLConstants
import org.ko.generator.constants.SQLConstants._
import org.ko.generator.constants.SchemaColumnNameConstants._
import org.ko.generator.util.ConverterHelper._
import org.ko.generator.util.ConverterSQLTypeHandler
import org.springframework.jdbc.core.{JdbcTemplate, RowMapper}

import scala.collection.JavaConverters._

private [core] abstract class AGenerator extends TGenerator {

  val jDBCTemplate = new JdbcTemplate()
  var mysqlDataSource: MysqlDataSource = _

  protected [core] def dataSource (dataSource: DataSource): Unit = {
    jDBCTemplate.setDataSource(dataSource)
    dataSource match {
      case mds: MysqlDataSource => mysqlDataSource = mds
      case _ => println("###match not case!")
    }
  }

  private [core] def findTableNames(database: String): List[String] = {
    jDBCTemplate.queryForList(INFORMATION_SCHEMA_TABLES, classOf[String], "sigma_server").asScala.toList
  }

  private [core] def findColumnByTableName (name: String):  List[Column] = {
    val database = mysqlDataSource.getDatabaseName    
    jDBCTemplate.query(SQLConstants.INFORMATION_SCHEMA_COLUMNS, new RowMapper[Column]() {
      override def mapRow(rs: ResultSet, i: Int): Column= {
        val columnName = rs.getString(COLUMN_NAME)
        val columnType = rs.getString(DATA_TYPE).toLowerCase()
        val charLength = int(rs.getString(CHARACTER_MAXIMUM_LENGTH))
        val precision = int(rs.getString(NUMERIC_PRECISION))
        val scale = int(rs.getString(NUMERIC_SCALE))
        var len = charLength + precision + scale
        if (scale != 0) len = len + 1
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
    }, name, database).asScala.toList
  }
}
