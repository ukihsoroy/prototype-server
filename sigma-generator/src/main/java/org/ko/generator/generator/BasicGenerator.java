package org.ko.generator.generator;

import org.ko.generator.bean.TableMetaData;

import java.util.List;

public class BasicGenerator extends AbstractGenerator {

    protected void generatorEntity () {
        List<TableMetaData> columns =  super.getTableMetaData("t_user");
        columns.forEach(column -> {
            System.out.println("ColumnName: " + column.getColumnName());
            System.out.println("Comment: " + column.getComment());
            System.out.println("DataType: " + column.getDataType());
            System.out.println("FieldName: " + column.getFieldName());
            System.out.println("Length: " + column.getLength());
            System.out.println("-----------");
        });
    }

    @Override
    public void generator() {

    }
}
