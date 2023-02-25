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

package com.trueprogramming.excel.excel.grammar.nonterm.binary;

import com.trueprogramming.excel.excel.grammar.lexicaltokens.CELL;
import com.trueprogramming.excel.excel.grammar.nonterm.Formula;
import com.trueprogramming.excel.excel.grammar.nonterm.FunctionCall;
import com.trueprogramming.excel.excel.grammar.nonterm.ParenthesisFormula;
import com.trueprogramming.excel.excel.grammar.nonterm.unary.Unary;

/**
 * FunctionCall ::= Formula BinOp Formula
 * BinOp ::= + | - | * | / | ^ | < | > | = | <= | >= | <>
 * <p>
 * Binary ::= Add | Sub | Mult | Div | Power | Lt | Gt | Eq | Leq | GtEq | Neq
 *
 * @author Massimo Caliman
 */
public abstract class Binary extends FunctionCall {

    protected final String op;
    protected final Formula lFormula;
    protected final Formula rFormula;

    Binary(Formula lFormula, String op, Formula rFormula) {
        this.lFormula = lFormula;
        this.op = op;
        this.rFormula = rFormula;
    }

    /*@NotNull
    @Override
    public String toString() {
        return "(" + operandToFormula(lFormula) + op + operandToFormula(rFormula) + ")";
    }*/


    @Override
    public String toString() {
        //return "(" + op + " " + operandToFormula(lFormula) + " " +  operandToFormula(rFormula) + ")";
        return clojurize(op, operandToFormula(lFormula), operandToFormula(rFormula));
    }

    protected String operandToFormula(Formula operand) {
        if (operand instanceof CELL || operand instanceof Unary) return operand.id();
        else if (operand instanceof ParenthesisFormula)
            return operandToFormulaParenthesisFormula((ParenthesisFormula) operand);
        else return operand.toString();
    }

    protected String operandToFormulaParenthesisFormula(ParenthesisFormula operand) {
        return operand.getFormula() instanceof Binary ?
                "(" + operand.getFormula() + ")" :
                "(" + operand.getFormula().getAddress(false) + ")";
    }

    public Formula getlFormula() {
        return lFormula;
    }

    public Formula getrFormula() {
        return rFormula;
    }

    protected String clojurize(String op, String term1, String term2) {
        return "(" + op + " " + term1 + " " + term2 + ")";
    }
}