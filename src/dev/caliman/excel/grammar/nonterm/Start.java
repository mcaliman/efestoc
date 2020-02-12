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

import dev.caliman.excel.grammar.lexicaltokens.SHEET;
import dev.caliman.excel.parser.AbstractParser;

import static dev.caliman.excel.parser.AbstractParser.cellAddress;

/**
 *
 * @author Massimo Caliman
 */
public abstract class Start {

    protected String sheetName;
    protected boolean singleSheet;
    private int sheetIndex;
    private int row;
    private int column;


    public void setSingleSheet(boolean singleSheet) {
        this.singleSheet = singleSheet;
    }

    public boolean isTerminal() {
        return false;
    }


    protected int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    protected int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public void setSHEET(SHEET tSHEET) {
        this.sheetIndex = tSHEET.getIndex();
        this.sheetName = tSHEET.getName();
    }

    public String getAddress() {
        return cellAddress(getRow(), getColumn(), sheetName);
    }

    public String getAddress(boolean sheet) {
        return sheet ? cellAddress(getRow(), getColumn(), sheetName) : cellAddress(getRow(), getColumn());
    }

    public String id() {
        return this.singleSheet ? cellAddress(getRow(), getColumn()) : AbstractParser.quoteIf(sheetName) + "!" + cellAddress(getRow(), getColumn());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.row;
        hash = 53 * hash + this.column;
        hash = 53 * hash + this.sheetIndex;
        return hash;
    }

    protected boolean isArea() {
        return this.row == -1 && this.column == -1;
    }

    public void setAsArea() {
        this.column = -1;
        this.row = -1;
    }

    @Override
    public boolean equals(final Object obj) {
        if(!(obj instanceof Start)) return false;
        final Start that = (Start) obj;
        if(this.row == -1 || that.row == -1)
            return (this.column == that.column && this.row == that.row && this.sheetIndex == that.sheetIndex);
        else return this.getAddress().equalsIgnoreCase(that.getAddress());
    }

    @Override
    public String toString() {
        return "";
    }

    public boolean testToFunctional(String text) {
        return ("(def " + this.id() + " " + this.toString() + ")").equals(text);
    }


}
