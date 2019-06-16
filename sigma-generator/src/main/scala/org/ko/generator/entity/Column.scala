package org.ko.generator.entity

case class Column (
                    columnName: String,
                    propertyName: String,
                    columnType: String,
                    propertyType: String,
                    primaryKey: Boolean,
                    length: Int,
                    common: String
                  )
