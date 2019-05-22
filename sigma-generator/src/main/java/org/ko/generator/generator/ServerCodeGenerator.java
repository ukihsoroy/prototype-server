package org.ko.generator.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.ko.generator.bean.*;
import org.ko.generator.helper.GeneratorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

import static org.ko.generator.constants.GeneratorConstants.MAIN_JAVA;
import static org.ko.generator.constants.GeneratorConstants.MAIN_RESOURCES;
import static org.ko.generator.helper.GeneratorHelper.formatPath;


@Component
public class ServerCodeGenerator extends AbstractGenerator {

    private static final Logger _LOGGER = LoggerFactory.getLogger(ServerCodeGenerator.class);


    @Autowired private Configuration freeMarkerConfiguration;

    private static List<String> ENV_JAVA = Arrays.asList(
            "bo.java.ftl",
            "command.java.ftl",
            "controller.java.ftl",
            "repository.java.ftl",
            "service.java.ftl",
            "repository.xml.ftl"
    );

    protected void generateStubs(String...tableNames) throws Exception {
        if(ArrayUtils.isEmpty(tableNames)){
            _LOGGER.info("no table name was specified");
            return;
        }

        String dir = new File(this.getClass().getClassLoader().getResource(".").toURI()).getAbsolutePath();

        int index = dir.indexOf("target");
        String moduleRoot = new File(dir.substring(0, index)).getParent().toString();
        moduleRoot = formatPath(moduleRoot, config.getModuleName());

        for (String table : tableNames) {
            //获取表全部字段
            List<TableMetaData> meta = super.getTableMetaData(table);

            //获取表名称驼峰, 去掉第一个下划线前字符
            String domainName = GeneratorHelper.buildDomainName(table);
            String variableName = GeneratorHelper.buildVariableName(table);

            //构建渲染变量
            Map<String, Object> model = new HashMap<>();
            //处理后表名称
            model.put("domainName", domainName);
            model.put("variableName", variableName);
            //表名称
            model.put("table", table);
            //表首字母(小名)
            String abbr = GeneratorHelper.getAbbr(table);
            model.put("abbr", abbr);
            //表的全部字段
            model.put("meta", meta);
            //生成时间
            model.put("now", DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));
            model.put("rootPackage", config.getRootPackage());
            //mybatis实体
            model.put("domainPackage", config.getDomainPackage());
            model.put("mapperPackage", config.getMapperPackage());


            for (String ftl : ENV_JAVA) {
                Template template = freeMarkerConfiguration.getTemplate(ftl);
                String fileName = getFilePath(moduleRoot, domainName, ftl);
                Writer out = new OutputStreamWriter(new FileOutputStream(new File(fileName)), "UTF-8");
                template.process(model, out);
                out.close();
                _LOGGER.info("generated {}", fileName);
            }

        }
    }

    private String getFilePath(String moduleRoot, String domainName, String ftl) {
        String[] ary = StringUtils.split(ftl, ".");
        String dirPath;
        switch (ary[1]) {
            case "java":
                dirPath = moduleRoot + MAIN_JAVA + GeneratorHelper.converterPackage(config.getRootPackage()) + "/" + ary[0] + "/";
                break;
            case "xml":
                dirPath = moduleRoot + MAIN_RESOURCES + GeneratorHelper.converterPackage(config.getXmlPackage()) + "/";
                break;
            default:
                throw new RuntimeException("happen error");
        }
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String contentName = domainName + StringUtils.capitalize(ary[0] + "." + ary[1]);
        return dirPath + contentName;
    }

    private void buildAllRepositories() throws Exception {
        List<String> tableNames = getAllTableNames();
        generateStubs(tableNames.toArray(new String[0]));
    }

    @Override
    public void generator() {
        try {
            if (properties.isEnable()) {
                buildAllRepositories();
            } else if (properties.getJava().getBasic().isEnable()) {
                generateStubs(properties.getTables());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
