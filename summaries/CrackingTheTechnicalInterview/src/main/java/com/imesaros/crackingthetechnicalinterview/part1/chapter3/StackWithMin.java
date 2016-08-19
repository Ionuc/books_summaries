package com.imesaros.crackingthetechnicalinterview.part1.chapter3;

/**
 * Problem 3.2
 * How would you design a stack which, in addition to push and pop, also has a function
 * min which returns the minimum element? Push, pop and min should all operate in
 * O(1) time.
 */
public class StackWithMin
{
    NodeWithMin nodeWithMin = null;

    public Integer min()
    {
        return nodeWithMin == null ? null : nodeWithMin.getMin();
    }

    public void push(int value)
    {
        if (nodeWithMin == null)
        {
            nodeWithMin = new NodeWithMin(value, value);
            return;
        }
        int min = Integer.min(value, nodeWithMin.getMin());
        NodeWithMin node = new NodeWithMin(value, min);
        node.setNext(nodeWithMin);
        nodeWithMin = node;
    }

    public Integer pop()
    {
        if (nodeWithMin == null)
        {
            return null;
        }
        NodeWithMin node = nodeWithMin;
        nodeWithMin = nodeWithMin.getNext();
        return node.getItem();
    }
}
