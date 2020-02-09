package org.ko.codegen;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

/**
 * description: 构建生成数据源 <br>
 * date: 2020/2/9 10:03 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public final class CodegenDatasourceBuild {

    /**
     * mysql数据源
     * @return
     */
    public static DataSource mysqlDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setDatabaseName("sigma_server");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("tiger");
        return mysqlDataSource;
    }
}
