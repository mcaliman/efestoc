/*
 * Efesto - Excel Formula Extractor System and Topological Ordering algorithm.
 * Copyright (C) 2017 Massimo Caliman mcaliman@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * If AGPL Version 3.0 terms are incompatible with your use of
 * Efesto, alternative license terms are available from Massimo Caliman
 * please direct inquiries about Efesto licensing to mcaliman@gmail.com
 */

package dataset;

import dev.caliman.excel.ToolkitCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Massimo Caliman
 */
class BinaryOpTest {

    @Test
    void testTest() throws Exception {
        String current = new java.io.File(".").getCanonicalPath();
        System.out.println("Current dir:" + current);
        ToolkitCommand cmd = new ToolkitCommand("Dataset/BinaryOp.xlsx");
        cmd.execute();
        System.out.println("ToFormula.");
        System.out.println("-------------");
        cmd.toFormula();

        assertTrue(cmd.testToFormula(0,
                "(def B2 5.0)",
                "(def A2 10.0)",
                "(def C6 (< A2 B2))",
                "(def C5 (/ A2 B2))",
                "(def C7 (> A2 B2))",
                "(def C12 (str A2 B2))",
                "(def C3 (- A2 B2))",
                "(def C13 (Math/pow A2 B2))",
                "(def C4 (* A2 B2))",
                "(def C10 (>= A2 B2))",
                "(def C11 (not= A2 B2))",
                "(def C2 (+ A2 B2))",
                "(def C8 (= A2 B2))",
                "(def C9 (<= A2 B2))")
        );
        cmd.write("Dataset/BinaryOp.clj");


    }
}
