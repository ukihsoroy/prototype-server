package org.ko.generator.properties

import org.springframework.boot.context.properties.ConfigurationProperties

case class GeneratorProperties @ConfigurationProperties(prefix = "sigma.generator")
(
  prefix: String,
  entity: EntityProperties,   //实体类生成配置
  controllerProperties: ControllerProperties, //controller生成配置
  serviceProperties: ServiceProperties, //service生成配置
  repositoryProperties: RepositoryProperties //数据层生成配置
)
