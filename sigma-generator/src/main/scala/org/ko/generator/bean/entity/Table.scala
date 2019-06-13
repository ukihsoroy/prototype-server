package org.ko.generator.bean.entity

case class Table (
                   dbName: String,
                   javaName: String,
                   common: String,
                   columns: List[Column]
                 )
