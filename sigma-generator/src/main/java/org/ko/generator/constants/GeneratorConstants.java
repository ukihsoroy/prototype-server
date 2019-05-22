package org.ko.generator.constants;

public class GeneratorConstants {

    public static final String MAIN_JAVA = "src/main/java/";
    public static final String MAIN_RESOURCES = "src/main/resources/";

    public static final class SchemaColumnName {
        public static final String COLUMN_NAME = "COLUMN_NAME";
        public static final String COLUMN_COMMENT = "COLUMN_COMMENT";
        public static final String DATA_TYPE = "DATA_TYPE";
        public static final String COLUMN_KEY = "COLUMN_KEY";
        public static final String PRI = "PRI";
        public static final String CHARACTER_MAXIMUM_LENGTH = "CHARACTER_MAXIMUM_LENGTH";
        public static final String NUMERIC_PRECISION = "NUMERIC_PRECISION";
        public static final String NUMERIC_SCALE = "NUMERIC_SCALE";
    }

    public static final class InformationSchemaSql {
        public static final String INFORMATION_SCHEMA_TABLES =
                "SELECT table_name FROM information_schema.tables WHERE table_schema = ?";
        public static final String INFORMATION_SCHEMA_COLUMNS =
                "SELECT * FROM information_schema.columns WHERE table_name= ? AND table_schema = ?";
        public static final String INFORMATION_SCHEMA_TABLE_DETAIL =
                "SELECT table_name name, table_comment comment FROM information_schema.tables WHERE table_schema = ?";
    }
}
