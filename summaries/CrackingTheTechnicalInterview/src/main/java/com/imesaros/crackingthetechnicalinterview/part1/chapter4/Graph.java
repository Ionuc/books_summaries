package com.imesaros.crackingthetechnicalinterview.part1.chapter4;


import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Map<String, GraphNode> nodes = new HashMap<>();

    public Graph addNode(String identifier, Integer value) {
        GraphNode node = new GraphNode(identifier, value);
        nodes.put(node.getIdentifier(), node);
        return this;
    }

    public Graph addEdge(String from, String to) {
        if (!nodes.containsKey(from) || !nodes.containsKey(to)) {
            return this;
        }
        GraphNode toNode = nodes.get(to);
        GraphNode fromNode = nodes.get(from);
        GraphNode previous = null;
        while (fromNode != null && !fromNode.getIdentifier().equals(to)) {
            previous = fromNode;
            fromNode = fromNode.getNext();
        }
        if (fromNode != null) {
            // Node to already existed in the list of from
            return this;
        }
        previous.setNext(new GraphNode(toNode.getIdentifier(), toNode.getItem()));
        return this;
    }

    public Map<String, GraphNode> getNodes() {
        return nodes;
    }
}
