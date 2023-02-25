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

package com.trueprogramming.excel.excel.lexicaltokens;

import com.trueprogramming.excel.excel.grammar.lexicaltokens.DATE;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO use LocalDate
 */
class DATETest {

    @SuppressWarnings("unused")
    Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    @Test
    void testIsTerminal() {
        Date date = new Date();
        DATE t = new DATE(date);
        assertTrue(t.isTerminal());
    }

    @Test
    void testEquals() {
        Date date = new Date();
        DATE date1 = new DATE(date);
        DATE date2 = new DATE(date);
        assertEquals(date1, date2);
        Date date3 = new Date();
        date3.setTime(date3.getTime() + 150);
        assertNotEquals(date1, date3);
    }

    @SuppressWarnings("deprecation")
    @Test
    void testToString() {
        Date date = new Date(2019 - 1900, Calendar.SEPTEMBER, 17);
        DATE date1 = new DATE(date);
        String result = date1.toString();
        System.out.println(result);
        assertEquals("(java.time.LocalDate/parse \"2019-09-17\")", result);
    }

}