package ${rootPackage}.constants;

public class ${entityName}Constants {

    public static class Columns {
    <#list columns as column>

        /**
         * ${column.comment}
         */
        public static String ${column.columnName?upper_case} = "${column.columnName}";
    </#list>
    }

}