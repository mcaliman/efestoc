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

package com.trueprogramming.excel.excel.grammar.nonterm;

import com.trueprogramming.excel.excel.grammar.lexicaltokens.RANGE;

/**
 * PrefixReferenceItem::= Prefix ReferenceItem
 * @author Massimo Caliman
 */
public final class PrefixReferenceItem extends Reference {

    private final Prefix prefix;

    private final String reference;

    private final RANGE range;


    public PrefixReferenceItem(Prefix prefix, String reference, RANGE range) {
        this.prefix = prefix;
        this.reference = reference;
        this.range = range;
        if(this.range != null) {
            setAsArea();
            add(this.range.values());
        }
    }

    @Override
    public String toString() {
        return isArea() ? values() : prefix + reference;
    }


    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        PrefixReferenceItem that = (PrefixReferenceItem) o;

        return this.prefix.equals(that.prefix) &&
                this.reference.equals(that.reference) &&
                this.sheetName.equals(that.sheetName);
    }

    public String id() {
        return !isArea() ? getAddress(!this.singleSheet) : prefix + reference;
    }

    private String values() {
        return values(this.range.getFirst().getRow(),
                this.range.getFirst().getColumn(),
                this.range.getLast().getRow(),
                this.range.getLast().getColumn(),
                vals,
                (range.isHorizzontal() || range.isVertical()));
    }

}