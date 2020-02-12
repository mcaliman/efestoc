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

package dev.caliman.excel.lexicaltokens;

import dev.caliman.excel.grammar.lexicaltokens.BOOL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Massimo Caliman
 */
class BOOLTest {

    @Test
    void testIsTerminal() {
        BOOL t = new BOOL(true);
        assertTrue(t.isTerminal());
    }


    @Test
    void testEquals() {
        BOOL tTRUE = new BOOL(true);
        BOOL tFALSE = new BOOL(false);
        assertNotEquals(tTRUE, tFALSE);
        BOOL tTRUE1 = new BOOL(true);
        assertEquals(tTRUE, tTRUE1);
    }

    @Test
    void testToString() {
        BOOL tTRUE = new BOOL(true);
        assertEquals("Boolean/TRUE", tTRUE.toString());
        BOOL tFALSE = new BOOL(false);
        assertEquals("Boolean/FALSE", tFALSE.toString());
    }
}