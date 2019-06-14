package org.ko.generator.constants

object SQLConstants {

  val INFORMATION_SCHEMA_TABLES = "SELECT table_name FROM information_schema.tables WHERE table_schema = ?"

  val INFORMATION_SCHEMA_COLUMNS = "SELECT * FROM information_schema.columns WHERE table_name= ? AND table_schema = ?"

  val INFORMATION_SCHEMA_TABLE_DETAIL = "SELECT table_name name, table_comment comment FROM information_schema.tables WHERE table_schema = ?"

}
