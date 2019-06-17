package org.ko.generator.entity;

import lombok.Data;

@Data
public class Column {

    public Column(String columnName, String propertyName, String columnType, String propertyType, boolean primaryKey, Integer length, String comment) {
        this.columnName = columnName;
        this.propertyName = propertyName;
        this.columnType = columnType;
        this.propertyType = propertyType;
        this.primaryKey = primaryKey;
        this.length = length;
        this.comment = comment;
    }

    private String columnName;
    private String propertyName;
    private String columnType;
    private String propertyType;
    private boolean primaryKey;
    private Integer length;
    private String comment;


}