package org.ko.generator.core

import freemarker.template.Configuration
import org.springframework.beans.factory.annotation.Autowired

class EntityGenerator @Autowired()(freemarker: Configuration) extends AGenerator {

  private val ENTITY_TEMPLATE_NAME = "entity.java.ftl"
  private val CONSTANTS_TEMPLATE_NAME = "constants.java.ftl"

  override def executor(): Unit = {

  }
}
