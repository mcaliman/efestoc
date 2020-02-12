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

package dev.caliman.excel.graph;

import dev.caliman.excel.grammar.nonterm.Start;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple node implementation.
 *
 * @author Massimo Caliman
 */
public class Node {


    private final List<Edge> neighbors;
    private Start data;

    public Node(Start data) {
        this.data = data;
        this.neighbors = new ArrayList<>();
    }

    private Edge getEdgeTo(Node dest) {
        for(Edge current : neighbors) if(current.dest().equals(dest)) return current;
        return null;
    }

    public List<Edge> edges() {
        return this.neighbors;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (data == null ? 0 : data.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Node other = (Node) obj;
        if(data == null) return other.data == null;
        else return data.equals(other.data);
    }

    public void addEdge(Edge edge) {
        if(!neighbors.contains(edge))
            neighbors.add(edge);
    }

    public void removeEdgeTo(Node neighbor) {
        Edge edge = getEdgeTo(neighbor);
        neighbors.remove(edge);
    }

    public Start value() {
        return data;
    }

    public void setValue(Start data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
