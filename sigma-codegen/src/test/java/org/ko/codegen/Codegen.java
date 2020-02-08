package org.ko.codegen;

import org.junit.Test;
import org.ko.codegen.core.CodegenTest;

/**
 * description: CodegenTests <br>
 * date: 2020/2/8 21:59 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class Codegen extends CodegenTest {

    @Test public void codegen() throws Exception {
        conditionCodegen.executor("tests");
    }

}
