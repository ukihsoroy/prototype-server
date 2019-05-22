package org.ko.generator.properties;

public class MybatisFileProperties {

    /**
     * 数据库域对象包名
     */
    private String domain = "domain";

    /**
     * 接口映射包名
     */
    private String mapper = "dao";

    /**
     * xml包名
     */
    private String xml = "mappers";

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getMapper() {
        return mapper;
    }

    public void setMapper(String mapper) {
        this.mapper = mapper;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }
}
