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

/**
 * ReferenceItem ::= CELL
 * | NamedRange
 * | REFERENCE_FUNCTION Arguments ‘)’
 * | VERTICAL_RANGE
 * | HORIZONTAL_RANGE
 * | ERROR_REF
 * @author Massimo Caliman
 */
public class ReferenceItem extends Reference {

    public String value;

    private int firstRow;
    private int firstColumn;
    private int lastRow;
    private int lastColumn;

    public ReferenceItem() {
    }

    @SuppressWarnings("SameParameterValue")
    public ReferenceItem(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }


    private boolean horizzontal_range() {
        return firstRow == lastRow && firstColumn != lastColumn;
    }

    private boolean vertical_range() {
        return firstColumn == lastColumn && firstRow != lastRow;
    }

    public String values() {
        return values(firstRow, firstColumn, lastRow, lastColumn, vals, (horizzontal_range() || vertical_range()));
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public void setFirstColumn(int firstColumn) {
        this.firstColumn = firstColumn;
    }

    public void setLastRow(int lastRow) {
        this.lastRow = lastRow;
    }

    public void setLastColumn(int lastColumn) {
        this.lastColumn = lastColumn;
    }

}
