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

package dev.caliman.excel.grammar.nonterm;

/**
 * ConstantArray ::= { ArrayColumns }
 *
 * @author Massimo Caliman
 */
public class ConstantArray extends Formula {

    private final Object[][] array;

    public ConstantArray(Object[][] array) {
        this.array = array;
    }

    public String id() {
        return this.getAddress();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append('[');
        for (Object[] internal : array) {
            str.append(internal[0]).append(' ');
        }
        if (str.charAt(str.length() - 1) == ' ') str.deleteCharAt(str.length() - 1);
        str.append(']');
        return str.toString();
    }

}
