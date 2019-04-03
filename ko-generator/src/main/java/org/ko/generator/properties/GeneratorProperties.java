package org.ko.generator.properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ko.generator")
public class GeneratorProperties {

    /**
     * <p>后台代码生成配置</p>
     */
    private JavaFileProperties java = new JavaFileProperties();

    /**
     * 前台视图生成配置
     */
    private ViewFileProperties view = new ViewFileProperties();

    /**
     * 自动生成全部配置
     */
    private ProjectProperties project = new ProjectProperties();

    /**
     * 生成项目模块名
     */
    private String moduleName;

    /**
     * 生成数据库表名
     */
    private String[] tables;

    /**
     * 开启后自动生成全库
     */
    private boolean enable;


    public JavaFileProperties getJava() {
        return java;
    }

    public void setJava(JavaFileProperties java) {
        this.java = java;
    }

    public ViewFileProperties getView() {
        return view;
    }

    public void setView(ViewFileProperties view) {
        this.view = view;
    }

    public ProjectProperties getProject() {
        return project;
    }

    public void setProject(ProjectProperties project) {
        this.project = project;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String[] getTables() {
        return tables;
    }

    public void setTables(String[] tables) {
        this.tables = tables;
    }

    public void setTables(String tables) {
        this.tables = StringUtils.split(tables, ",");
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
