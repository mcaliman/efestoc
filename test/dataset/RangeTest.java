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

import com.trueprogramming.excel.excel.ToolkitCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RangeTest {

    @Test
    void testTest() throws Exception {
        String current = new java.io.File(".").getCanonicalPath();
        System.out.println("Current dir:" + current);
        ToolkitCommand cmd = new ToolkitCommand("Dataset/Range.xlsx");
        cmd.execute();
        System.out.println("ToFormula.");
        System.out.println("-------------");
        cmd.toFormula();

        assertTrue(cmd.testToFormula(0,
                "(def A1:A4 [ 1.0 2.0 3.0 4.0 ])",
                "(def B1:E1 [ 5.0 6.0 7.0 8.0 ])",
                "(def A7 (reduce + A1:A4))",
                "(def A8 (reduce + B1:E1))"
                )
        );
        cmd.write("Dataset/Range.clj");


    }
}
