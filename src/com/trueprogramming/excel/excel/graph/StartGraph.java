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

import com.trueprogramming.excel.excel.grammar.nonterm.Formula;
import com.trueprogramming.excel.excel.grammar.nonterm.Start;
import com.trueprogramming.excel.excel.grammar.nonterm.binary.Binary;
import com.trueprogramming.excel.excel.parser.StartList;
import com.trueprogramming.excel.excel.grammar.lexicaltokens.EXCEL_FUNCTION;


import java.util.*;

public class StartGraph {


    private final HashMap<Start, Node> graph;

    public StartGraph() {
        graph = new HashMap<>();
    }

    public void addNode(Start start) {
        if(start.isTerminal()) return;
        Node u = graph.get(start);
        if(u == null) {
            u = new Node(start);
            graph.put(start, u);
        } else if(notEquals(u, start)) {
            u.setValue(start);
            graph.put(start, u);
        }
    }

    public void addEdge(Start x,  Start y) {
        if(x.isTerminal() || y.isTerminal()) return;
        if(x.getAddress().equalsIgnoreCase(y.getAddress())) return;
        Node u = graph.get(x);
        Node v = graph.get(y);
        Edge edge = new Edge(u, v);
        u.addEdge(edge);
    }

    public void add( Binary operation) {
        var left = operation.getlFormula();
        var right = operation.getrFormula();
        addNode(right);
        addNode(left);
        addNode(operation);
        addEdge(right, operation);
        addEdge(left, operation);
    }

    public void add( EXCEL_FUNCTION function) {
        Formula[] args = function.getArgs();
        for(Formula arg : args)
            addNode(arg);
        addNode(function);
        for(Formula arg : args)
            addEdge(arg, function);
    }

    /**
     * Use kahn Top Sort
     *
     * @return sorted StartList
     */
    
    public StartList topologicalSort() {
        var result = new StartList();
        Queue<Node> queue = new ArrayDeque<>();
        Collection<Node> nodes = graph.values();
        List<Edge> edges = edges();
        for(Node v : nodes)
            if(notHasIncomingEdges(v, edges))
                queue.add(v);
        while(!queue.isEmpty()) {
            Node v = queue.poll();
            result.add(v.value());
            List<Edge> outgoingEdges = outgoingEdges(v);
            for(Edge e : outgoingEdges) {
                Node s = e.src();
                Node t = e.dest();
                removeEdge(s.value(), t.value());
                Node end = e.dest();
                List<Edge> edges1 = this.edges();
                if(notHasIncomingEdges(end, edges1))
                    queue.add(end);
            }
        }
        if(!edges().isEmpty()) {
            System.err.println("error when sort!. this.edges().size()=" + this.edges().size());
            return result;
        }
        return result;
    }

    private void removeEdge(Start x, Start y) {
        Node u = graph.get(x);
        Node v = graph.get(y);
        u.removeEdgeTo(v);
    }

    
    private List<Edge> edges() {
        List<Edge> results = new ArrayList<>();
        Collection<Node> nodes = graph.values();
        for(var node : nodes) results.addAll(node.edges());
        return results;
    }

    private boolean notHasIncomingEdges(Node v, List<Edge> allEdges) {
        for(var edge : allEdges)
            if(edge.dest().equals(v)) return false;
        return true;
    }

    
    private List<Edge> outgoingEdges(Node v) {
        List<Edge> outgoingEdges = new ArrayList<>();
        List<Edge> edges = edges();
        edges.stream().filter((edge) -> (edge.src().equals(v))).forEachOrdered(outgoingEdges::add);
        return outgoingEdges;
    }

    private boolean notEquals(Node u,  Start start) {
        Start start1 = u.value();
        return notEquals(start1, start);
    }

    private boolean notEquals(Start start1, Start start) {
        return !Objects.requireNonNull(start1.toString()).equals(start.toString());
    }

}
