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
    public static final String prefix = "t_";

    /**
     * 后端对应的配置属性类
     */
    public static final class BackEndProperties {

        public static final String module = "sigma-browser";

        public static final String rootPackage = "org.ko.sigma.rest";

        public static final String controllerTemplate = "controller.java.ftl";

        public static final String serviceTemplate = "service.java.ftl";

        public static final String serviceImplTemplate = "serviceImpl.java.ftl";

        public static final String repositoryTemplate = "repository.java.ftl";

        public static final String repositoryXMLTemplate = "repository.xml.ftl";

        public static final String conditionTemplate = "condition.java.ftl";

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