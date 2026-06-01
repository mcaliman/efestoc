package com.trueprogramming.excel.excel.grammar.lexicaltokens;

import com.trueprogramming.excel.excel.grammar.nonterm.IConstant;
import com.trueprogramming.excel.excel.grammar.nonterm.Formula;

/**
 * Text lexical token.
 */
public record TEXT(String value) implements IConstant, Formula {
    public static TEXT of(String value) {
        return new TEXT(value);
    }

    @Override
    public String toString() {
        return "\"" + value + "\"";
    }

    public boolean isTerminal() {
        return true;
    }
}
