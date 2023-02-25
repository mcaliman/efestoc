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
class BasicTest {

    @Test
    void testTest() throws Exception {
        String dir = "test/Datasets/Extra/";
        String dataSet = dir + "Basic.xlsx";
        String output = dir + "Basic.clj";
        ToolkitCommand toolkitCommand = new ToolkitCommand(dataSet);
        toolkitCommand.execute();
        System.out.println("ToFormula.");
        toolkitCommand.toFormula();
        assertTrue(toolkitCommand.testToFormula(
                0,
                "(def B4 10.0)",
                "(def B5 20.0)",
                "(def B1 (+ B5 B4))"
        ));
        toolkitCommand.write(output);
    }
}
