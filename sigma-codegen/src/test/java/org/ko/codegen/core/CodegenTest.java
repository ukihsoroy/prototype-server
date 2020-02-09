package org.ko.codegen.core;

import org.junit.jupiter.api.BeforeEach;
import org.ko.codegen.CodegenDatasourceBuild;

/**
 * description: CodegenTestParent <br>
 * date: 2020/2/8 22:00 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class CodegenTest {

    protected ConditionCodegen conditionCodegen = new ConditionCodegen(CodegenDatasourceBuild.mysqlDataSource());

}
