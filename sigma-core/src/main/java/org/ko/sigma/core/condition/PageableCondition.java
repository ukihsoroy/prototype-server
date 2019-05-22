package org.ko.sigma.core.condition;

import lombok.Data;
import org.ko.sigma.core.bean.SerializeBean;

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
