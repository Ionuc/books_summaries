package com.imesaros.crackingthetechnicalinterview.part1.chapter4

import spock.lang.Specification

import static com.imesaros.crackingthetechnicalinterview.part1.chapter4.GraphHelper.isPath

class GraphHelperSpecs extends Specification {
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

    def 'should check if there is a path between two nodes'() {
        expect:
        isPath(graph, start, end) == expected

        where:
        start                 | end                   | expected
        new GraphNode("A", 1) | new GraphNode("D", 4) | true
        new GraphNode("A", 1) | new GraphNode("E", 5) | true
        new GraphNode("C", 3) | new GraphNode("E", 5) | false
        new GraphNode("D", 4) | new GraphNode("E", 5) | false
    }
}
