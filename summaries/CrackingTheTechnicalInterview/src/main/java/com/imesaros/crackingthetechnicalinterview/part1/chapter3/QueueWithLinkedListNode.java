package com.imesaros.crackingthetechnicalinterview.part1.chapter3;

import com.imesaros.crackingthetechnicalinterview.part1.chapter2.LinkedListNode;

/**
 * Problem 3.0
 * Implement a custom queue
 */
public class QueueWithLinkedListNode
{
    LinkedListNode first = null;
    LinkedListNode last  = null;

    public void enqueue(Integer value)
    {
        LinkedListNode node = new LinkedListNode(value);
        if (last == null)
        {
            first = last = node;
            return;
        }
        last.setNext(node);
        last = node;
    }

    public Integer dequeue()
    {
        if (first == null)
        {
            return null;
        }
        Integer val = first.getItem();
        first = first.getNext();
        if (first == null)
        {
            last = null;
        }
        return val;
    }
}
