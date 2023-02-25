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
class Excel_Multisheet1_Test {

    @Test
    void testTest() throws Exception {
        ToolkitCommand toolkitCommand = new ToolkitCommand("test/multisheet-1.xlsx");
        toolkitCommand.execute();
        System.out.println("ToFormula.");
        toolkitCommand.toFormula();
        assertTrue(toolkitCommand.testToFormula(0,
                "(def SheetB!A1:A3 [ 1.0 2.0 3.0 ])",
                "(def SheetA!A1 (reduce + SheetB!A1:A3))"
        ));
        toolkitCommand.write("test/multisheet-1.clj");
    }
}
