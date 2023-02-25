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

package com.trueprogramming.excel.excel.graph;



class Edge {

    private final Node src;
    private final Node dest;

    public Edge(Node src, Node dest) {
        this.src = src;
        this.dest = dest;
    }

    public Node src() {
        return this.src;
    }

    public Node dest() {
        return this.dest;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((src == null) ? 0 : src.hashCode());
        result = prime * result + ((dest == null) ? 0 : dest.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if(this == object) return true;
        if(object == null) return false;
        if(getClass() != object.getClass()) return false;
        Edge edge = (Edge) object;
        if(src == null) {
            if(edge.src != null) return false;
        } else if(!src.equals(edge.src)) return false;

        if(dest == null) return edge.dest == null;
        else return dest.equals(edge.dest);
    }

    @Override
    public String toString() {
        return "(" + src + " -> " + dest + ")";
    }

}
