package org.ko.generator.constants

object SQLConstants {

  val INFORMATION_SCHEMA_TABLES = "SELECT table_name FROM information_schema.tables WHERE table_schema = ?"

  val INFORMATION_SCHEMA_COLUMNS = "SELECT * FROM information_schema.columns WHERE table_schema = ? AND table_name= ?"

  val INFORMATION_SCHEMA_TABLE_DETAIL = "SELECT table_comment FROM information_schema.tables WHERE table_schema = ? and table_name = ?"

}
