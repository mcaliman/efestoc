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

package com.trueprogramming.excel.excel.test.datasets.extra;

import com.trueprogramming.excel.excel.ToolkitCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Massimo Caliman
 */
class Excel_02_VerticalRangeIndex_Test {
    /**
     * ToFormula: OK
     * ToFunctional: OK
     */
    @Test
    void testTest() throws Exception {

        ToolkitCommand toolkitCommand = new ToolkitCommand("test/02-vertical-range-index.xlsx");
        toolkitCommand.execute();
        System.out.println("ToFormula.");
        System.out.println("----------");
        toolkitCommand.toFormula();
        assertTrue(toolkitCommand.testToFormula(0,
                "(def I1:I7 [ 0.0 1.0 2.0 3.0 4.0 5.0 6.0 ])",
                "(def B9 (index I1:I7 4))"));
        toolkitCommand.write("test/02-vertical-range-index.clj");
    }
}
