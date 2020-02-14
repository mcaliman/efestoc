package dataset;

import dev.caliman.excel.ToolkitCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 */
public class BuiltinAbsFunctionTest {

    /**
     * Formula Plain Text: ABS!C2 = ABS(A2)
     * Formula Plain Text: ABS!D2 = ABS(B2)
     * @throws Exception
     */
    @Test
    void testBuiltinLogicalFunction() throws Exception {
        ToolkitCommand cmd = new ToolkitCommand("Dataset/builtin-abs-function-test.xlsx");
        cmd.execute();
        System.out.println("ToFormula.");
        System.out.println("-------------");
        cmd.toFormula();

        assertTrue(cmd.testToFormula(0,
                "(def B2 -10.0)",
                "(def A2 10.0)",
                "(def D2 (abs B2))",
                "(def C2 (abs A2))"
                )
        );
        cmd.write("Dataset/builtin-abs-function-test.clj");


    }
}
