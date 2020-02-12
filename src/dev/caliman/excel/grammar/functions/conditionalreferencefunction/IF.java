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

package dev.caliman.excel.grammar.functions.conditionalreferencefunction;

import dev.caliman.excel.grammar.lexicaltokens.REF_FUNCTION_COND;
import dev.caliman.excel.grammar.nonterm.Formula;

/**
 * @author Massimo Caliman
 */
public class IF extends REF_FUNCTION_COND {

    public IF(Formula... args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "(" + getName() + " " + argumentsToFormula() + ")";
    }

    protected String getName() {
        return "if";
    }

    protected String argumentsToFormula() {
        if (args == null || args.length == 0) return "Missing";
        var buff = new StringBuilder();
        for (Formula arg : args) buff.append(argumentToFormula(arg)).append(" ");
        if (buff.charAt(buff.length() - 1) == ' ') buff.deleteCharAt(buff.length() - 1);
        return buff.toString();
    }
}
