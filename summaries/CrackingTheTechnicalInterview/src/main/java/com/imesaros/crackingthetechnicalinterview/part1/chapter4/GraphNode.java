package com.imesaros.crackingthetechnicalinterview.part1.chapter4;

import com.imesaros.crackingthetechnicalinterview.part1.chapter2.LinkedListNode;

import static com.imesaros.crackingthetechnicalinterview.part1.chapter4.GraphState.NOT_VISITED;

public class GraphNode extends LinkedListNode {
    private final String identifier;
    private GraphState state = NOT_VISITED;

    public GraphNode(String identifier, int item) {
        super(item);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public GraphNode getNext() {
        return (GraphNode) super.getNext();
    }

    public GraphState getState() {
        return state;
    }

    public void setState(GraphState state) {
        this.state = state;
    }
}
