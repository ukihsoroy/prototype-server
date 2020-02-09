package org.ko.codegen;

import org.junit.Test;
import org.ko.codegen.core.CodegenTest;

/**
 * description: 代码生成 <br>
 * date: 2020/2/8 21:59 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class Codegen extends CodegenTest {

    String[] tables = new String[]{"t_user"};

    @Test public void conditionGen() throws Exception {
        conditionCodegen.executor(tables);
    }

}
