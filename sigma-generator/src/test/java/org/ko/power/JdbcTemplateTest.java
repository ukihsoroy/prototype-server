package org.ko.power;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.generator.bean.Config;
import org.ko.generator.bean.TableMetaData;
import org.ko.generator.helper.GeneratorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JdbcTemplateTest {

    @Autowired private JdbcTemplate jdbcTemplate;

    @Autowired private Config config;

    @Value("${spring.datasource.name}") String dbName;

    @Test
    public void test1 () {
        assert jdbcTemplate != null;
        String param = "art-prototype";
        String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = ?";
        List<String> tableNames = jdbcTemplate.queryForList(sql, String.class, param);
        tableNames.forEach(System.out::println);
    }

    @Test
    public void test2 () {
        String sql = "select * from information_schema.columns where table_name= ? and table_schema = ?";
        List<TableMetaData> tableMetaData = jdbcTemplate.query(sql, (rs, i) -> {
            TableMetaData data = new TableMetaData();
            data.setColumnName(rs.getString("COLUMN_NAME"));
            data.setComment(StringUtils.trimToEmpty(rs.getString("COLUMN_COMMENT")));
            data.setDataType(rs.getString("DATA_TYPE").toLowerCase());
            data.setPrimaryKey("PRI".equalsIgnoreCase(rs.getString("COLUMN_KEY")));
            String len, scale = null;
            do {
                len = rs.getString("CHARACTER_MAXIMUM_LENGTH");
                if(len != null){
                    break;
                }
                len = rs.getString("NUMERIC_PRECISION");
                if(len != null){
                    scale = rs.getString("NUMERIC_SCALE");
                }
            } while (false);
            Integer length = GeneratorHelper.convertToInt(len);
            if(StringUtils.isNotBlank(scale)){
                length = length + GeneratorHelper.convertToInt(scale) + 1;
            }
            data.setLength(length);
            return data;
        }, "art_link", dbName);
        assert tableMetaData.size() > 0;
    }
}
