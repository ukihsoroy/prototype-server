package org.ko.codegen.entity;

/**
 * 数据库列配置
 */
public class Column {

    /**
     * 数据库字段名称
     */
    private String columnName;

    /**
     * 属性名称
     */
    private String propertyName;

    /**
     * 数据库字段类型
     */
    private String columnType;

    /**
     * 属性类型
     */
    private String propertyType;

    /**
     * 是否是主键
     */
    private boolean primaryKey;

    /**
     * 数据长度
     */
    private Integer length;

    /**
     * 备注
     */
    private String comment;

    /**
     * 全参数构造函数
     * @param columnName 数据库字段名称
     * @param propertyName java Bean 属性名称
     * @param columnType 数据库字段类型
     * @param propertyType java Bean 属性类型
     * @param primaryKey 是否是主键
     * @param length 数据库长度
     * @param comment 备注
     */
    public Column(String columnName, String propertyName, String columnType, String propertyType, boolean primaryKey, Integer length, String comment) {
        this.columnName = columnName;
        this.propertyName = propertyName;
        this.columnType = columnType;
        this.propertyType = propertyType;
        this.primaryKey = primaryKey;
        this.length = length;
        this.comment = comment;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
