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

package dev.caliman.excel.parser;

import dev.caliman.excel.grammar.nonterm.Start;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Massimo Caliman
 */
public final class StartList extends ArrayList<Start> implements List<Start> {

    public StartList() {
    }

    @Override
    public boolean add(Start object) {
        if(!contains(object)) {
            return super.add(object);
        }
        return true;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public boolean singleton() {
        return this.size() == 1;
    }

    private boolean testToFunctional(int index, String text) {
        return this.get(index).testToFunctional(text);
    }

    public boolean testToFunctional(int offset, String... text) {
        if(size() == 0) return false;
        boolean test = true;
        for(int i = 0; i < text.length; i++)
            test &= this.testToFunctional(i + offset, text[i]);
        return test;
    }
}
