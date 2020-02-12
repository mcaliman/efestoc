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

package dev.caliman.excel.test.datasets.extra;

import dev.caliman.excel.ToolkitCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Excel_902_TerminalsFormulas_Test {

    @Test
    void testTest() throws Exception {
        long t = System.currentTimeMillis();
        ToolkitCommand toolkitCommand = new ToolkitCommand("test/902-terminals-formulas.xlsx");
        toolkitCommand.execute();
        long elapsed = System.currentTimeMillis() - t;
        System.out.println("elapsed: " + elapsed / 1000 + " s.");
        System.out.println("ToFormula.");
        toolkitCommand.toFormula();
        assertTrue(toolkitCommand.testToFormula(
                0,
                "(def A1 Boolean/TRUE)",
                "(def A5 \"1/0\")",
                "(def A3 24.0)",
                "(def A4 \"This is a string\")",
                "(def A2 1.838226)",
                "(def A9 (if A1 A5 A3))",
                "(def A7 (if A1 A2 A3))",
                "(def A8 (if A1 A4 A7))"
        ));
        toolkitCommand.write("test/902-terminals-formulas.clj");
    }

}
