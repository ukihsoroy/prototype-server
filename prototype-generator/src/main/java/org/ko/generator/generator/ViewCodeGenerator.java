package org.ko.generator.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.ko.generator.bean.TableMetaData;
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

import static org.ko.generator.helper.GeneratorHelper.formatPath;

@Component
public class ViewCodeGenerator extends AbstractGenerator {

    private static List<String> ELEMENT_UI = Arrays.asList(
            "detail.html.ftl",
            "detail.js.ftl",
            "edit.html.ftl",
            "edit.js.ftl",
            "list.html.ftl",
            "list.js.ftl"
    );
    private static final Logger _LOGGER = LoggerFactory.getLogger(ServerCodeGenerator.class);


    @Autowired
    private Configuration freeMarkerConfiguration;

    protected void generateViews(String...tableNames) throws Exception {
        if(ArrayUtils.isEmpty(tableNames)){
            _LOGGER.info("no table name was specified");
            return;
        }

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
            model.put("jsPath", config.getJsPath());
            model.put("htmlPath", config.getHtmlPath());
            for (String ftl : ELEMENT_UI) {
                Template template = freeMarkerConfiguration.getTemplate(ftl);
                String fileName = getFilePath(variableName, ftl);
                Writer out = new OutputStreamWriter(new FileOutputStream(new File(fileName)), "UTF-8");
                template.process(model, out);
                out.close();
                _LOGGER.info("generated {}", fileName);
            }

        }
    }

    private String getFilePath(String domainName, String ftl) {
        String[] ary = StringUtils.split(ftl, ".");
        String dirPath;
        switch (ary[1]) {
            case "html":
                dirPath = config.getHtmlPath() + domainName;
                break;
            case "js":
                dirPath = config.getJsPath() + domainName;
                break;
            default:
                throw new RuntimeException("happen error");
        }
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return formatPath(dirPath, ary[0] + "." + ary[1]);
    }

    private void buildAllViews() throws Exception {
        List<String> tableNames = getAllTableNames();
        generateViews(tableNames.toArray(new String[]{}));
    }

    @Override
    public void generator() {
        try {
            if (properties.isEnable()) {
                buildAllViews();
            } else if (properties.getView().isEnable()) {
                generateViews(properties.getTables());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
