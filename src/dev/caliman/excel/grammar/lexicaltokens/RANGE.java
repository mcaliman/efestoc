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

package dev.caliman.excel.grammar.lexicaltokens;

import dev.caliman.excel.grammar.nonterm.Reference;

import java.util.List;

public class RANGE extends Reference {

    private final CELL first;
    private final CELL last;

    public RANGE(CELL first, CELL end) {
        this.first = first;
        this.last = end;
    }

    public List<Object> values() {
        return this.vals;
    }

    public void add(Object values) {
        vals.add(values);
    }

    public CELL getFirst() {
        return first;
    }

    public CELL getLast() {
        return last;
    }

    public String toString() {
        return first.getAddress() + ":" + last.getAddress();
    }


    public boolean isHorizzontal() {
        return first.getRow() == last.getRow() && first.getColumn() != last.getColumn();
    }

    public boolean isVertical() {
        return first.getColumn() == last.getColumn() && first.getRow() != last.getRow();
    }

}
