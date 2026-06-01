package com.trueprogramming.excel.excel.grammar.lexicaltokens;

import com.trueprogramming.excel.excel.grammar.nonterm.IConstant;
import com.trueprogramming.excel.excel.grammar.nonterm.Formula;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Date lexical token.
 */
public record DATE(Date value) implements IConstant, Formula {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static DATE of(Date value) {
        return new DATE(value);
    }

    @Override
    public boolean isTerminal() {
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DATE other = (DATE) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return value == null ? "" : "(java.time.LocalDate/parse \"" + DATE_FORMAT.format(value) + "\")";
    }
}
