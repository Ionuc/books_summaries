package com.imesaros.crackingthetechnicalinterview.part1.chapter3;

import com.imesaros.crackingthetechnicalinterview.part1.chapter2.LinkedListNode;

public class NodeWithMin extends LinkedListNode
{
    private final int min;

    public NodeWithMin(int item, int min)
    {
        super(item);
        this.min = min;
    }

    @Override
    public NodeWithMin getNext()
    {
        return (NodeWithMin) super.getNext();
    }

    public int getMin()
    {
        return min;
    }
}
