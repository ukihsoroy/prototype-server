package ${rootPackage}.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>${comment}</p>
 */
@Data
@TableName("${name}")
public class ${entityName} implements Serializable {

<#list columns as column>
    /**
     * ${column.comment!}
     */
    private ${column.propertyType!} ${column.propertyName!};
</#list>

}