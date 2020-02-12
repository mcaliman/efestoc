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
package dev.caliman.excel.grammar.functions.builtin;

import dev.caliman.excel.grammar.lexicaltokens.EXCEL_FUNCTION;
import dev.caliman.excel.grammar.nonterm.Formula;

/**
 * CEILING function
 * <p>
 * Applies To: Excel 2016 Excel 2013 Excel 2010 Excel 2007 Excel 2016 for Mac
 * More... This article describes the formula syntax and usage of the CEILING
 * function in Microsoft Excel.
 * <p>
 * Description
 * <p>
 * Returns number rounded up, away from zero, to the nearest multiple of
 * significance. For example, if you want to avoid using pennies in your prices
 * and your product is priced at $4.42, use the formula =CEILING(4.42,0.05) to
 * round prices up to the nearest nickel.
 * <p>
 * Syntax
 * <p>
 * CEILING(number, significance) The CEILING function syntax has the following
 * arguments:
 * <p>
 * Number Required. The value you want to round.
 * <p>
 * Significance Required. The multiple to which you want to round.
 * <p>
 * Remarks If either argument is nonnumeric, CEILING returns the #VALUE! error
 * value. Regardless of the sign of number, a value is rounded up when adjusted
 * away from zero. If number is an exact multiple of significance, no rounding
 * occurs. If number is negative, and significance is negative, the value is
 * rounded down, away from zero.
 * <p>
 * If number is negative, and significance is positive, the value is rounded up
 * towards zero.
 */
public class CEILING extends EXCEL_FUNCTION {

    public CEILING(Formula... args) {
        this.args = args;
    }

}
