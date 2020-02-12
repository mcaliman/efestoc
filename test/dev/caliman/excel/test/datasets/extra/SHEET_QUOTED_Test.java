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

/**
 * @author Massimo Caliman
 */
class SHEET_QUOTED_Test {

    @Test
    void testTest() throws Exception {
        long t = System.currentTimeMillis();
        ToolkitCommand toolkitCommand = new ToolkitCommand("test/SHEET-QUOTED.xlsx");
        toolkitCommand.execute();
        long elapsed = System.currentTimeMillis() - t;
        System.out.println("elapsed: " + elapsed / 1000 + " s.");
        System.out.println("ToFunctional.");
        toolkitCommand.toFormula();
        assertTrue(toolkitCommand.testToFormula(
                0,
                "(def 'Other Sheet Name With Spaces'!A1 30.0)",
                "(def 'Sheet Name With Spaces'!A1 10.0)",
                "(def 'Other Sheet Name With Spaces'!A2 (+ 'Other Sheet Name With Spaces'!A1 30))",
                "(def 'Sheet Name With Spaces'!A2 (+ 'Sheet Name With Spaces'!A1 10))"
        ));
        toolkitCommand.write("test/SHEET-QUOTED.clj");
    }

}
