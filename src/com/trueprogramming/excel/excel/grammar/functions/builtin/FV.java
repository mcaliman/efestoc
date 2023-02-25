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
package com.trueprogramming.excel.excel.grammar.functions.builtin;

import com.trueprogramming.excel.excel.grammar.lexicaltokens.EXCEL_FUNCTION;
import com.trueprogramming.excel.excel.grammar.nonterm.Formula;

/**
 * FV, one of the financial functions, calculates the future value of an
 * investment based on a constant interest rate. You can use FV with either
 * periodic, constant payments, or a single lump parseSum payment.
 * <p>
 * Syntax FV(rate,nper,pmt,[pv],[type]) For a more complete description of the
 * arguments in FV and for more information on annuity functions, see PV.
 * <p>
 * The FV function syntax has the following arguments:
 * <p>
 * Rate Required. The interest rate per period. Nper Required. The total number
 * of payment periods in an annuity. Pmt Required. The payment made each period;
 * it cannot change over the life of the annuity. Typically, pmt contains
 * principal and interest but no other fees or taxes. If pmt is omitted, you
 * must include the pv argument.
 * <p>
 * Pv Optional. The present value, or the lump-parseSum amount that a series of
 * future payments is worth right now. If pv is omitted, it is assumed to be 0
 * (zero), and you must include the pmt argument. Type Optional. The number 0 or
 * 1 and indicates when payments are due. If type is omitted, it is assumed to
 * be 0.
 */
public class FV extends EXCEL_FUNCTION {

    public FV(Formula... args) {
        this.args = args;
    }

}
