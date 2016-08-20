package com.imesaros.crackingthetechnicalinterview.part1.chapter4;


import java.util.ArrayList;
import java.util.List;

public class GraphHelper {
    /**
     * Problem 4.2
     * Given a directed graph, design an algorithm to find out whether there is a route
     * between two nodes.
     */
    public static boolean isPath(Graph graph, GraphNode start, GraphNode end) {
        if (!graph.contains(start) || !graph.contains(end)) {
            return false;
        }
        List<String> nodesFromPath = new ArrayList<>();
        GraphTraversal.dfs(graph, start.getIdentifier(), n -> nodesFromPath.add(n));
        return nodesFromPath.contains(end.getIdentifier());
    }
}
