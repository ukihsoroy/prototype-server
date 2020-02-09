package org.ko.codegen.core;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.ko.codegen.constants.CodegenConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * description: ConditionGeneratro <br>
 * date: 2020/2/8 21:38 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class ConditionCodegen extends AbstractCodegen {

    /**
     * 初始化配置数据源
     * @param dataSource
     */
    public ConditionCodegen(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public void executor(String... names) throws Exception {
        for (String name : names) {
            //表名字
            String entityName = reformatTable(name, CodegenConstants.PREFIX);

            //包名称
            String packageName = name.replaceFirst(CodegenConstants.PREFIX, "").split("_")[0];

            String dir = new File(this.getClass().getClassLoader().getResource(".").toURI()).getAbsolutePath();
            int index = dir.indexOf("target");
            String moduleRoot = new File(dir.substring(0, index)).getParent();

            String javaDir = moduleRoot + "/" + CodegenConstants.BackEndProperties.MODULE + ROOT_DIR
                    + CodegenConstants.BackEndProperties.ROOT_PACKAGE.replaceAll("\\.", "/") + "/"
                    + packageName + CodegenConstants.BackEndProperties.CONDITION_PACKAGE;

            String repositoryFileName = javaDir + "Query" + entityName + "Condition.java";

            Map<String, Object> params = new HashMap<>();

            params.put("name", name);
            params.put("entityName", entityName);
            params.put("rootPackage", CodegenConstants.BackEndProperties.ROOT_PACKAGE + "." + packageName);

            if (StringUtils.isNotEmpty(repositoryFileName)) {
                File dirFile = new File(javaDir);
                if (!dirFile.exists()) dirFile.mkdirs();
                Template template = cfg.getTemplate(CodegenConstants.BackEndProperties.CONDITION_TEMPLATE);
                Writer out = new OutputStreamWriter(new FileOutputStream(new File(repositoryFileName)),
                        CodegenConstants.CHARSET_NAME);
                template.process(params, out);
                out.close();
            }

        }
    }
}
