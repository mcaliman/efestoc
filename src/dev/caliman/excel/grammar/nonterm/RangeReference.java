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

import dev.caliman.excel.grammar.lexicaltokens.RANGE;

import java.util.Objects;


/**
 * RangeReference::= Reference : Reference
 * or
 * RangeReference::= RANGE
 * @author Massimo Caliman
 */
public final class RangeReference extends Reference {

    private final RANGE range;

    public RangeReference(RANGE range) {
        this.range = range;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        RangeReference that = (RangeReference) o;
        return Objects.requireNonNull(that.toString()).equals(this.toString());
    }

    @Override
    public String toString() {
        return values();
    }

    private String values() {
        return values(range.getFirst().getRow(), range.getFirst().getColumn(),
                range.getLast().getRow(), range.getLast().getColumn(), vals,
                (range.isHorizzontal() || range.isVertical()));
    }

    public String id() {
        return this.singleSheet ? range.toString() : sheetName + "!" + range.toString();
    }

}