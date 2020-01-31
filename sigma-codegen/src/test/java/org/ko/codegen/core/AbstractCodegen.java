package org.ko.codegen.core;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.commons.lang3.StringUtils;
import org.ko.codegen.constants.SQLConstants;
import org.ko.codegen.entity.Column;
import org.ko.codegen.util.ConverterSQLTypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.ko.codegen.constants.SQLConstants.INFORMATION_SCHEMA_TABLES;
import static org.ko.codegen.constants.SchemaColumnNameConstants.*;

/**
 * description: AbstractCodegen <br>
 * date: 1/31/2020 20:07 <br>
 *
 * @author zhiyuan.shen <br>
 * @version 1.0 <br>
 */
public abstract class AbstractCodegen implements ICodegen {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractCodegen.class);

    protected static final String ROOT_DIR = "/src/main/java/";

    protected JdbcTemplate jDBCTemplate = new JdbcTemplate();

    protected MysqlDataSource mysqlDataSource;

    public void dataSource (DataSource dataSource){
        jDBCTemplate.setDataSource(dataSource);

        if (dataSource instanceof MysqlDataSource) {
            mysqlDataSource = (MysqlDataSource) dataSource;
        } else {
            logger.info("###no support other datasource!");
        }
    }

    protected List<String> findTableNames(String database){
        return jDBCTemplate.queryForList(INFORMATION_SCHEMA_TABLES, String.class, database);
    }


    protected String findTableComment (String name){
        String database = mysqlDataSource.getDatabaseName();
        return jDBCTemplate.queryForObject(SQLConstants.INFORMATION_SCHEMA_TABLE_DETAIL, String.class, database, name);
    }

    private List<Column> findColumnByTableName (String name){
        String database = mysqlDataSource.getDatabaseName();
        jDBCTemplate.query(SQLConstants.INFORMATION_SCHEMA_COLUMNS, new RowMapper<Column>() {


            @Override
            public Column mapRow(ResultSet rs, int i) throws SQLException {
                String columnName = rs.getString(COLUMN_NAME);
                String columnType = rs.getString(DATA_TYPE).toLowerCase();
                Integer charLength = rs.getInt(CHARACTER_MAXIMUM_LENGTH);
                Integer precision = rs.getInt(NUMERIC_PRECISION);
                Integer scale = rs.getInt(NUMERIC_SCALE);
                Integer len = charLength + precision + scale;
                if (scale != 0) len = len + 1;
                return new Column(
                        columnName,
                        mapUnderscoreToCamelCase(columnName),
                        columnType,
                        ConverterSQLTypeHandler.format(columnType),
                        PRI.equalsIgnoreCase(rs.getString(COLUMN_KEY)),
                        len,
                        StringUtils.trimToEmpty(rs.getString(COLUMN_COMMENT))
                );
            }

        }, database, name);
    }

    /**
     *
     * @param value
     * @return
     */
    protected def int(value: String): Int = {
        var res = 0
        if (StringUtils.isNotEmpty(value)) res = value.toInt
        res
    }

    protected String mapUnderscoreToCamelCase(String name){
        String[] sp = name.split("_");
        StringBuilder res = new StringBuilder();
        res.append(sp[0]);
        sp.tail.map(x => x.replaceFirst(x.charAt(0).toString, x.charAt(0).toUpper.toString)).foreach(res.append)
        res.toString()
    }

    protected def reformatTable (name: String, prefix: String): String = {
        var entityName = ""
        if (name.startsWith(prefix)) {
            entityName = mapUnderscoreToCamelCase(name.replaceFirst(prefix, ""))
        } else {
            entityName = mapUnderscoreToCamelCase(name)
        }
        entityName.replaceFirst(entityName.charAt(0).toString, entityName.charAt(0).toUpper.toString)
    }
}
