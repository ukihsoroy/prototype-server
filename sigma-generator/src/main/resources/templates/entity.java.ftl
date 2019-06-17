package ${rootPackage}.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import java.io.Serializable;

/**
 * <p>${comment}</p>
 * @author K.O
 */
@Data
@TableName("${name}")
public class ${entityName} implements Serializable {

<#list columns as column>
    /**
     * ${column.comment}
     */
    <#if column.primaryKey>
    @TableId(type = IdType.AUTO)
    </#if>
    private ${column.propertyType} ${column.propertyName};

    </#list>

}