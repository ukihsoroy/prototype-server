package org.ko.codegen.constants;

/**
 * description: sql常量 <br>
 * date: 1/31/2020 19:43 <br>
 *
 * @author zhiyuan.shen <br>
 * @version 1.0 <br>
 */
public class SQLConstants {

    /**
     * 查询全部的表名字
     */
    public static final String INFORMATION_SCHEMA_TABLES = "SELECT table_name FROM information_schema.tables WHERE table_schema = ?";

    /**
     * 查询一张表的全部字段
     */
    public static final String INFORMATION_SCHEMA_COLUMNS = "SELECT * FROM information_schema.columns WHERE table_schema = ? AND table_name= ? and COLUMN_NAME not in ('version', 'enable', 'create_user', 'gmt_create', 'modified_user', 'gmt_modified')";

    /**
     * 查询表的备注
     */
    public static final String INFORMATION_SCHEMA_TABLE_DETAIL = "SELECT table_comment FROM information_schema.tables WHERE table_schema = ? and table_name = ?";

}
