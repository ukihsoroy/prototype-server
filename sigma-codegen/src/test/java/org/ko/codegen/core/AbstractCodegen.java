package org.ko.codegen.core;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.ko.codegen.constants.CodegenConstants;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.ko.codegen.constants.SQLConstants.INFORMATION_SCHEMA_TABLES;
import static org.ko.codegen.constants.SchemaColumnNameConstants.*;

/**
 * description: 代码生成模板方法 <br>
 * date: 1/31/2020 20:07 <br>
 *
 * @author zhiyuan.shen <br>
 * @version 1.0 <br>
 */
public abstract class AbstractCodegen implements ICodegen {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractCodegen.class);

    protected static final String ROOT_DIR = "/src/main/java/";

    /**
     * 模板引擎
     */
    protected static Configuration cfg;

    protected JdbcTemplate jDBCTemplate = new JdbcTemplate();

    protected MysqlDataSource mysqlDataSource;

    static {
        cfg = new Configuration(Configuration.getVersion());
        cfg.setDefaultEncoding(CodegenConstants.CHARSET_NAME);
        cfg.setClassForTemplateLoading(AbstractCodegen.class, "/");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    protected void setDataSource (DataSource dataSource){
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
        return jDBCTemplate.query(SQLConstants.INFORMATION_SCHEMA_COLUMNS, new RowMapper<Column>() {
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

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /** 下划线转驼峰 */
    protected static String mapUnderscoreToCamelCase(String value) {
        value = value.toLowerCase();
        Matcher matcher = linePattern.matcher(value);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    protected String reformatTable (String name, String prefix){
        String entityName = "";
        if (name.startsWith(prefix)) {
            entityName = mapUnderscoreToCamelCase(name.replaceFirst(prefix, ""));
        } else {
            entityName = mapUnderscoreToCamelCase(name);
        }
        return entityName.replaceFirst(String.valueOf(entityName.charAt(0)),
                String.valueOf(entityName.charAt(0)).toUpperCase());
    }
}
