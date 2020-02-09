package org.ko.codegen.constants;

/**
 * description: 生成基础配置常量类 <br>
 * date: 1/31/2020 19:58 <br>
 *
 * @author zhiyuan.shen <br>
 * @version 1.0 <br>
 */
public final class CodegenConstants {

    /**
     * 数据库表名字前缀
     */
    public static final String PREFIX = "t_";

    public static final String CHARSET_NAME = "UTF-8";

    /**
     * 后端对应的配置属性类
     */
    public static final class BackEndProperties {

        public static final String MODULE = "sigma-gentest";

        public static final String ROOT_PACKAGE = "org.ko.sigma.rest";

        public static final String controllerTemplate = "controller.java.ftl";

        public static final String serviceTemplate = "service.java.ftl";

        public static final String serviceImplTemplate = "serviceImpl.java.ftl";

        public static final String repositoryTemplate = "repository.java.ftl";

        public static final String repositoryXMLTemplate = "repository.xml.ftl";

        public static final String CONDITION_TEMPLATE = "condition.java.ftl";

        public static final String CONDITION_PACKAGE = "/condition/";

        public static final String dtoTemplate = "dto.java.ftl";

    }

    /**
     * 数据库实体配置类
     */
    public static final class EntityProperties {

        public static final String module = "sigma-mysql";

        public static final String rootPackage = "org.ko.sigma.data";

        public static final String entityTemplate = "entity.java.ftl";

        public static final String constantsTemplate = "constants.java.ftl";
    }

}