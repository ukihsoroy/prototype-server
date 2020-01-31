package org.ko.codegen.entity;

import java.util.List;

/**
 * description: 数据库表的常量 <br>
 * date: 1/31/2020 19:45 <br>
 *
 * @author zhiyuan.shen <br>
 * @version 1.0 <br>
 */
public class Table {

    /**
     * 数据库表名称
     */
    private String name;

    /**
     * 实体名称
     */
    private String entityName;

    /**
     * 表评论
     */
    private String comment;

    /**
     * 该表的全部字段
     */
    private List<Column> columns;

    /**
     * 全参数构造函数
     */
    public Table(String name, String entityName, String comment, List<Column> columns) {
        this.name = name;
        this.entityName = entityName;
        this.comment = comment;
        this.columns = columns;
    }

    public Table() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
