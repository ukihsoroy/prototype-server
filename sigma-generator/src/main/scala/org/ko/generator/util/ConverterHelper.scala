package org.ko.generator.util

import org.apache.commons.lang3.StringUtils

object ConverterHelper {

  /**
    *
    * @param value
    * @return
    */
  def int(value: String): Int = {
    var res = 0
    if (StringUtils.isNotEmpty(value)) res = value.toInt
    res
  }

  def mapUnderscoreToCamelCase(column: String): String = {
    val sp = column.split("_")
    val res = new StringBuilder
    res.append(sp.head)
    sp.tail.map(x => x.replaceFirst(x.charAt(0).toString, x.charAt(0).toUpper.toString)).foreach(res.append)
    res.toString()
  }
}
