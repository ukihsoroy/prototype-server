package org.ko.generator.properties;

public class ProjectProperties {

    /**
     * 开启拷贝项目
     */
    private boolean enable = false;

    /**
     * 项目名称
     */
    private String name = "generator-app";

    /**
     * 生成路径
     */
    private String path = "D://";

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
