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

import dev.caliman.excel.grammar.lexicaltokens.TEXT;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TEXTTest {

    @Test
    void testIsTerminal() {
        TEXT t = new TEXT("12");
        assertTrue(t.isTerminal());
    }

    @Test
    void testEquals() {
        TEXT f1 = new TEXT("75");
        TEXT f2 = new TEXT("36");
        assertNotEquals(f1, f2);
        TEXT f11 = new TEXT("75");
        assertEquals(f1, f11);
    }

    @Test
    void testToString() {
        TEXT f1 = new TEXT("75");
        System.out.println(f1.toString());
        assertEquals("\"75\"", f1.toString());
    }
}