package com.imesaros.crackingthetechnicalinterview.part1.chapter4

import spock.lang.Specification

import java.util.function.Consumer

import static GraphTraversal.bfs
import static GraphTraversal.dfs

class GraphTraversalSpecs extends Specification {

    List<String> list = new ArrayList<>()
    Consumer<String> consumer = { value -> list.add(value); };

    /**
     * A -> B -> G
     * B -> C -> E -> F -> G
     * C -> D
     * D
     * E -> D
     * F -> E
     * G -> F
     */
    Graph graph = new Graph()
            .addNode("A", 1)
            .addNode("B", 2)
            .addNode("C", 3)
            .addNode("D", 4)
            .addNode("E", 5)
            .addNode("F", 6)
            .addNode("G", 7)
            .addEdge("A", "B")
            .addEdge("A", "G")
            .addEdge("B", "C")
            .addEdge("B", "E")
            .addEdge("B", "F")
            .addEdge("B", "G")
            .addEdge("C", "D")
            .addEdge("E", "D")
            .addEdge("F", "E")
            .addEdge("G", "F")


    def 'should travers in DFS'() {
        when:
        dfs(graph, start, consumer)

        then:
        list == expected

        where:
        start | expected
        "A"   | ["A", "B", "C", "D", "E", "F", "G"]
        "B"   | ["B", "C", "D", "E", "F", "G"]
        "C"   | ["C", "D"]
        "D"   | ["D"]
        "E"   | ["E", "D"]
        "F"   | ["F", "E", "D"]
        "G"   | ["G", "F", "E", "D"]
    }

    def 'should travers in BFS'() {
        when:
        bfs(graph, start, consumer)

        then:
        list == expected

        where:
        start | expected
        "A"   | ["A", "B", "G", "C", "E", "F", "D"]
        "B"   | ["B", "C", "E", "F", "G", "D"]
        "C"   | ["C", "D"]
        "D"   | ["D"]
        "E"   | ["E", "D"]
        "F"   | ["F", "E", "D"]
        "G"   | ["G", "F", "E", "D"]
    }
}
