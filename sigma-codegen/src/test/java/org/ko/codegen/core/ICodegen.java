package org.ko.codegen.core;

import java.util.List;

/**
 * description: 生成类接口 <br>
 * date: 1/31/2020 20:05 <br>
 *
 * @author zhiyuan.shen <br>
 * @version 1.0 <br>
 */
public interface ICodegen {

    /**
     * 执行生成逻辑
     * @param names 数据库的表名称
     */
    void executor(String... names);
}
