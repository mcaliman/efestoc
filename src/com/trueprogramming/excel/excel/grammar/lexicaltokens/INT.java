package com.trueprogramming.excel.excel.grammar.lexicaltokens;

import com.trueprogramming.excel.excel.grammar.nonterm.IConstant;
import com.trueprogramming.excel.excel.grammar.nonterm.Formula;

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
