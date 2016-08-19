package com.imesaros.crackingthetechnicalinterview.part1.chapter2;

public class LinkedListNode
{
    private int            item;
    private LinkedListNode next;

    public LinkedListNode(int item)
    {
        this.item = item;
    }

    public int getItem()
    {
        return item;
    }

    public LinkedListNode getNext()
    {
        return next;
    }

    public void setNext(LinkedListNode next)
    {
        this.next = next;
    }
}
