package com.imesaros.crackingthetechnicalinterview.chapter2;

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

    public void appendToTail(int d)
    {
        LinkedListNode node = new LinkedListNode(d);
        LinkedListNode h = this;
        while (h.next != null)
        {
            h = h.next;
        }
        h.next = node;
    }

    public LinkedListNode remove(LinkedListNode head, int i)
    {
        if (head == null)
        {
            return null;
        }
        while (head.next != null && head.item != i)
        {
            head = head.next;
        }
        return head;
    }
}
