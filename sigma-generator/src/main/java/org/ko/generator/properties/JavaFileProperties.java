package org.ko.generator.properties;

public class JavaFileProperties {

    /**
     * 项目基础包
     */
    private String rootPackage = "org.ko.api";

    /**
     * controller service repository xml
     */
    private BasicFileProperties basic = new BasicFileProperties();

    /**
     * domain dao xml
     */
    private MybatisFileProperties mybatis = new MybatisFileProperties();

    public String getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }

    public BasicFileProperties getBasic() {
        return basic;
    }

    public void setBasic(BasicFileProperties basic) {
        this.basic = basic;
    }

    public MybatisFileProperties getMybatis() {
        return mybatis;
    }

    public void setMybatis(MybatisFileProperties mybatis) {
        this.mybatis = mybatis;
    }
}
