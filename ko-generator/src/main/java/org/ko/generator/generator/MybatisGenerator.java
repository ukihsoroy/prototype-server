package org.ko.generator.generator;

import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.ko.generator.bean.ColumnValue;
import org.ko.generator.bean.Table;
import org.ko.generator.bean.TableMetaData;
import org.ko.generator.helper.GeneratorHelper;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

import static org.ko.generator.constants.GeneratorConstants.MAIN_JAVA;
import static org.ko.generator.helper.GeneratorHelper.formatPath;

@Component
public class MybatisGenerator extends AbstractGenerator {

	private static final Logger _LOGGER = LoggerFactory.getLogger(MybatisGenerator.class);
	
	private static final String TEMPLATE_NAME = "generator.ftl";
	
	private static final String META_TEMPLATE_NAME = "constants.java.ftl";

	@Autowired private freemarker.template.Configuration freeMarkerConfiguration;

	private String mbgXmlPath = "d:/generator.xml";
	
	protected void generateDomainConstants(String...tableNames) throws Exception {
		if(ArrayUtils.isEmpty(tableNames)){
			_LOGGER.info("no table name was specified");
			return;
		}
		
		for(String name : tableNames){
			String domainName = GeneratorHelper.buildDomainName(name);
			
			List<TableMetaData> data = super.getTableMetaData(name);
			
			List<String> columns = getColumnNames(data);
			
			Map<String, List<ColumnValue>> values = new HashMap<>();
			for(TableMetaData d : data){
				if (d.getComment().contains("#")) {
					// 列名
					String[] elements = StringUtils.split(d.getColumnName(), "_");
					String className = "";
					for(int j = 0; j < elements.length; j++){
						className += StringUtils.capitalize(elements[j]);
					}
					
					// 注释
					String[] parts = d.getComment().split("\\s+");
					for (int i = 1; i < parts.length; i++) {
						if(parts[i].indexOf("-") > 0 && parts[i].indexOf("#") > 0){
							String text = StringUtils.replace(parts[i], "-", " ");
							text = StringUtils.replace(text, "#", " ");
							String[] segs = text.split("\\s+");
							
							String type = super.getJavaTypeName(d);
							
							String value = "";
							String javaComment = "";
							String varName = "";
							do {
								if (segs.length > 0) {
									value = segs[0];
								}
								if (segs.length > 1) {
									javaComment = segs[1];
								}
								if (segs.length > 2) {
									varName = segs[2];
								}
							} while (false);
							
							ColumnValue cv = new ColumnValue();
							cv.setComment(javaComment);
							cv.setName(varName);
							cv.setType(type);
							cv.setValue(value);
							cv.setColumnName(d.getColumnName());
							cv.setFieldName(d.getFieldName());
							
							List<ColumnValue> cvList = values.get(className);
							if(cvList == null){
								cvList = new ArrayList<>();
							}
							cvList.add(cv);
							values.put(className, cvList);
						}
					}
				}
			}
			
			Map<String, Object> model = new HashMap<>();
			model.put("Table", StringUtils.capitalize(domainName));
			model.put("columns", columns);
			model.put("values", values);
			model.put("meta", data);
			model.put("now", DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));
			model.put("rootPackage", config.getRootPackage());
			
			String dir = new File(this.getClass().getClassLoader().getResource(".").toURI()).getAbsolutePath();
			
			int index = dir.indexOf("target");
			String moduleRoot = new File(dir.substring(0, index)).getParent().toString();
			moduleRoot = formatPath(moduleRoot, config.getModuleName());
			
			if(StringUtils.isNotBlank(getJavaFileOutputFolder())){
				moduleRoot = getJavaFileOutputFolder();
			}
			
			if(!new File(moduleRoot).isDirectory()){
				System.out.println(moduleRoot + " doens't exist");
			}
			
			String javaDir = moduleRoot + MAIN_JAVA + GeneratorHelper.converterPackage(config.getRootPackage()) + "/constants/";
			String javaFileName = javaDir + domainName + "Constants.java";
			
			String javaFileDir = FilenameUtils.getFullPath(javaFileName);
			FileUtils.forceMkdir(new File(javaFileDir));
			
			Template template = freeMarkerConfiguration.getTemplate(META_TEMPLATE_NAME);
			Writer out = new OutputStreamWriter(new FileOutputStream(new File(javaFileName)), "UTF-8");
			template.process(model, out);
			out.close();

			_LOGGER.info("generated {}", javaFileName);
		}
		
	}
	
	protected void generateStubs(String...tableNames) throws Exception {
		if(ArrayUtils.isEmpty(tableNames)){
			_LOGGER.info("no table name was specified");
			return;
		}
		
		makeXml(tableNames);
		
		File configFile = new File(mbgXmlPath);
		List<String> warnings = new ArrayList<>();
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		_LOGGER.info("mybatis generated done !");
	}
	
	private void makeXml(String...tableNames) throws Exception {
		List<Table> tables = new ArrayList<>();
		for(String name : tableNames){
			Table table = new Table();
			table.setName(name);
			
			String[] elements = StringUtils.split(name, "_");
			String domainName = "";
			if(elements.length == 1){
				domainName = StringUtils.capitalize(elements[0]);
			}else{
				for(int i = 1; i < elements.length; i++){
					domainName += StringUtils.capitalize(elements[i]);
				}
			}
			table.setDomainName(domainName);
			
			tables.add(table);
		}
		
		Map<String, Object> model = new HashMap<>();
		model.put("connectionUrl", url);
		model.put("username", username);
		model.put("password", password);
		model.put("tables", tables);
		model.put("module", config.getModuleName());
		model.put("rootPackage", config.getRootPackage());
		model.put("domainPackage", config.getDomainPackage());
		model.put("mapperPackage", config.getMapperPackage());
		model.put("xmlPackage", config.getXmlPackage());
		
		Template template = freeMarkerConfiguration.getTemplate(TEMPLATE_NAME);
		Writer out = new OutputStreamWriter(new FileOutputStream(new File(mbgXmlPath)), "UTF-8");
		template.process(model, out);
		out.close();
	}

	protected void buildAllMappers() throws Exception {
		List<String> tableNames = getAllTableNames();
		String[] tables = tableNames.toArray(new String[]{});

		generateStubs(tables);
		generateDomainConstants(tables);
	}

	@Override
	public void generator() {
		try {
			if (properties.isEnable()) {
				buildAllMappers();
			} else {
				generateStubs(properties.getTables());
				generateDomainConstants(properties.getTables());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
