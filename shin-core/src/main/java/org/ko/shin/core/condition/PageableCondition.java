package org.ko.shin.core.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.shin.core.bean.SerializeBean;

@Data
@EqualsAndHashCode(callSuper = true)
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
