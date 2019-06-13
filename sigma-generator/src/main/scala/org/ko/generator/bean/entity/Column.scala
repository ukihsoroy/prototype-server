package org.ko.generator.bean.entity

case class Column (
                    dbName: String,
                    javaName: String,
                    dbType: String,
                    javaType: String,
                    primaryKey: Boolean,
                    length: Int,
                    common: String
                  )
