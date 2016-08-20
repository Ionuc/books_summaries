package com.imesaros.crackingthetechnicalinterview.part1.chapter4;

import java.util.Map;
import java.util.function.Consumer;

import static com.imesaros.crackingthetechnicalinterview.part1.chapter4.GraphState.NOT_VISITED;
import static com.imesaros.crackingthetechnicalinterview.part1.chapter4.GraphState.VISITED;

public class GraphTraversal {

    /**
     * Problem 4.0
     */
    public static void dfs(Graph graph, String startIdentifier, Consumer<String> consumer) {
        GraphNode start = graph.getNodes().get(startIdentifier);
        dfs(start, graph.getNodes(), consumer);
    }

    private static void dfs(GraphNode node, Map<String, GraphNode> nodes, Consumer<String> consumer) {
        if (node == null) {
            return;
        }
        consumer.accept(node.getIdentifier());
        node.setState(VISITED);
        processSiblingDfs(node, nodes, consumer);
    }

    private static void processSiblingDfs(GraphNode node, Map<String, GraphNode> nodes, Consumer<String> consumer) {
        while (node.getNext() != null) {
            GraphNode child = nodes.get(node.getNext().getIdentifier());
            if (child.getState() == NOT_VISITED) {
                dfs(child, nodes, consumer);
            }
            node = node.getNext();
        }
    }

    /**
     * Problem 4.0
     */
    public static void bfs(Graph graph, String startIdentifier, Consumer<String> consumer) {
        GraphNode start = graph.getNodes().get(startIdentifier);
        bfs(start, graph.getNodes(), consumer);
    }

    private static void bfs(GraphNode node, Map<String, GraphNode> nodes, Consumer<String> consumer) {
        if (node == null) {
            return;
        }
        processChildBfs(node, nodes, consumer);
        processSiblingBfs(node, nodes, consumer);
        while (node.getNext() != null) {
            bfs(nodes.get(node.getNext().getIdentifier()), nodes, consumer);
            node = node.getNext();
        }
    }

    private static void processSiblingBfs(GraphNode node, Map<String, GraphNode> nodes, Consumer<String> consumer) {
        while (node.getNext() != null) {
            processChildBfs(node.getNext(), nodes, consumer);
            node = node.getNext();
        }
    }

    private static void processChildBfs(GraphNode node, Map<String, GraphNode> nodes, Consumer<String> consumer) {
        GraphNode child = nodes.get(node.getIdentifier());
        if (child.getState() == NOT_VISITED) {
            consumer.accept(child.getIdentifier());
            child.setState(VISITED);
        }
    }
}
