package org.ko.prototype.core.condition;

import lombok.Data;
import org.ko.prototype.core.bean.SerializeBean;

@Data
public class PageableCondition extends SerializeBean {

    /**
     * 页数
     */
    private int page;

    /**
     * 条数
     */
    private int limit;

}
