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

package com.trueprogramming.excel.excel.grammar.lexicaltokens;

import com.trueprogramming.excel.excel.grammar.nonterm.IConstant;
import com.trueprogramming.excel.excel.grammar.nonterm.Formula;

import java.util.Objects;

/**
 * Integer lexical token.
 */
public record INT(Integer value) implements IConstant, Formula {
    public static INT of(Integer value) {
        return new INT(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public boolean isTerminal() {
        return true;
    }
}

/**
 * Float lexical token.
 */
public record FLOAT(Double value) implements IConstant, Formula {
    public static FLOAT of(Double value) {
        return new FLOAT(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public boolean isTerminal() {
        return true;
    }
}

/**
 * Boolean lexical token.
 */
package com.trueprogramming.excel.excel.grammar.lexicaltokens;

import com.trueprogramming.excel.excel.grammar.nonterm.IConstant;
import com.trueprogramming.excel.excel.grammar.nonterm.Formula;
import java.util.Objects;

/**
 * Boolean lexical token.
 */
public record BOOL(Boolean value) implements IConstant, Formula {
    public static BOOL of(Boolean value) {
        return new BOOL(value);
    }

    @Override
    public String toString() {
        return value != null && value ? "Boolean/TRUE" : "Boolean/FALSE";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BOOL that = (BOOL) obj;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public boolean isTerminal() {
        return true;
    }
}

    public static BOOL of(Boolean value) {
        return new BOOL(value);
    }

    @Override
    public String toString() {
        return value != null && value ? "Boolean/TRUE" : "Boolean/FALSE";
    }
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        final BOOL that = (BOOL) obj;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public String toString() {
        return value != null && value ? "Boolean/TRUE" : "Boolean/FALSE";
    }

}
