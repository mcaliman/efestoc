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

package dev.caliman.excel.grammar.lexicaltokens;

import dev.caliman.excel.grammar.nonterm.Constant;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author Massimo Caliman
 */
public final class DATE extends Constant {
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private final Date value;

    @SuppressWarnings("unused")
    public DATE(Date value) {
        this.value = value;
    }

    public boolean isTerminal() {
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        final DATE other = (DATE) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return format(value);
    }

    private String format(final Date date) {
        return date == null ? "" : "(java.time.LocalDate/parse \"" + DATE_FORMAT.format(date) + "\")";
    }
}
