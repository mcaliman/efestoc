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

package dev.caliman.excel.test;

import dev.caliman.excel.parser.AbstractParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mcaliman
 */
class ExcelHelperTest {

    ExcelHelperTest() {
    }

    @Test
    void testColumnAsLetter() {
        System.out.println("columnAsLetter");
        Assertions.assertEquals("A", AbstractParser.columnAsLetter(0));
        assertEquals("B", AbstractParser.columnAsLetter(1));
    }

    @Test
    void testCellAddress_int_int() {
        System.out.println("cellAddress(row,col)");
        assertEquals("A1", AbstractParser.cellAddress(0, 0));

    }

    @Test
    void testCellAddress_3args() {
        System.out.println("cellAddress(row,col,sheetname)");

        String result = AbstractParser.cellAddress(0, 0, "Sheet");
        System.out.println(result);
        assertEquals("Sheet!A1", result);
    }

}
