package com.imesaros.crackingthetechnicalinterview.part1.chapter3;

import com.imesaros.crackingthetechnicalinterview.part1.chapter2.LinkedListNode;

/**
 * Problem 3.0
 * Implement a custom stack
 */
public class StackWithLinkedListNode
{
    LinkedListNode top = null;

    public Integer pop()
    {
        if (top == null)
        {
            return null;
        }
        int result = top.getItem();
        top = top.getNext();
        return result;
    }

    public void push(Integer value)
    {
        LinkedListNode node = new LinkedListNode(value);
        node.setNext(top);
        top = node;
    }
}
