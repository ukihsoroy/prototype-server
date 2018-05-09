package org.ko.prototpye.core.base;

import com.alibaba.fastjson.JSONObject;
import lombok.ToString;
import org.ko.prototpye.core.common.GlobalVariables;

/**
 * 
 * BaseObject
 *
 * @author <A>calder</A>
 *
 */
@ToString
public abstract class BaseObject implements BaseConvert, BaseData,
        GlobalVariables {

    private static final long serialVersionUID = -5288814752256367412L;

    @Override
    public String convertToJson() {
        return JSONObject.toJSONString(this);
    }

}
