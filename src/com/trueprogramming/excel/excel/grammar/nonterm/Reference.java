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

import com.trueprogramming.excel.excel.parser.AbstractParser;

import java.util.ArrayList;
import java.util.List;


/**
 * Reference ::= ReferenceItem
 * | Reference : Reference
 * | Reference  Reference
 * | ( Union )
 * | ( Reference)
 * | Prefix ReferenceItem
 * | Prefix UDF Arguments )
 * | DynamicDataExchange
 *
 *
 *
 * @author Massimo Caliman
 */
public abstract class Reference extends Formula {

    protected final List<Object> vals = new ArrayList<>();

    public void add(List<Object> values) {
        vals.addAll(values);
    }

    protected String values(int fRow, int fCol, int lRow, int lCol, List<Object> list, boolean isHorizzontalOrVerticalRange) {
        if(list.isEmpty()) return "[]";
        if(isHorizzontalOrVerticalRange) {
            StringBuilder buff = new StringBuilder();
            buff.append("[").append(" ");
            for(Object element : list) buff.append(toString(element)).append(" ");
            if(buff.length() > 1) buff.deleteCharAt(buff.length() - 1);
            buff.append(" ").append("]");
            return buff.toString();
        } else {
            StringBuilder buff = new StringBuilder();
            buff.append("[");
            int index = 0;
            for(int row = fRow; row <= lRow; row++) {
                buff.append("[");
                for(int col = fCol; col <= lCol; col++) {
                    Object element = list.get(index);
                    buff.append(toString(element)).append(" ");
                    index++;
                }
                if (buff.length() > 1) buff.deleteCharAt(buff.length() - 1);
                buff.append("]");
            }
            buff.append("]");
            return buff.toString();
        }
    }

    /*protected String values(int fRow, int fCol, int lRow, int lCol, List<Object> list, boolean isHorizzontalOrVerticalRange) {
        if(list.isEmpty()) return "[]";
        if(isHorizzontalOrVerticalRange) {
            StringBuilder buff = new StringBuilder();
            buff.append("[").append(" ");
            for(Object element : list) buff.append(toString(element)).append(" ");
            if(buff.length() > 1) buff.deleteCharAt(buff.length() - 1);
            buff.append(" ").append("]");
            return buff.toString();
        } else {
            StringBuilder buff = new StringBuilder();
            buff.append("[");
            int index = 0;
            for(int row = fRow; row <= lRow; row++) {
                buff.append("[");
                for(int col = fCol; col <= lCol; col++) {
                    Object element = list.get(index);
                    buff.append(toString(element)).append(" ");
                    index++;
                }
                if(buff.length() > 1) buff.deleteCharAt(buff.length() - 1);
                buff.append("]");
            }
            buff.append("]");
            return buff.toString();
        }
    }
    * */

    private String toString(Object value) {
        String string = value instanceof String ? AbstractParser.quote(value.toString()) : value.toString();
        return super.toString() + string;
    }
}
