package org.ko.generator.conf;

import org.ko.generator.bean.Config;
import org.ko.generator.properties.GeneratorProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.util.Properties;

import static org.ko.generator.helper.GeneratorHelper.formatPath;

@Configuration
@EnableConfigurationProperties(GeneratorProperties.class)
public class GeneratorConfig {

    @Bean
    public Config config(GeneratorProperties properties) {
        Config config = new Config();
        config.setModuleName(properties.getModuleName());
        config.setRootPackage(properties.getJava().getRootPackage());
        config.setDomainPackage(properties.getJava().getMybatis().getDomain());
        config.setMapperPackage(properties.getJava().getMybatis().getMapper());
        config.setXmlPackage(properties.getJava().getMybatis().getXml());
        config.setHtmlPath(formatPath(properties.getView().getPath(), properties.getView().getHtml()));
        config.setJsPath(formatPath(properties.getView().getPath(), properties.getView().getJs()));
        return config;
    }

    @Bean
    public FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean () {
        FreeMarkerConfigurationFactoryBean factoryBean = new FreeMarkerConfigurationFactoryBean();
        factoryBean.setTemplateLoaderPath("template");
        Properties properties = new Properties();
        properties.setProperty("defaultEncoding", "UTF-8");
        factoryBean.setFreemarkerSettings(properties);
        return factoryBean;
    }

}
