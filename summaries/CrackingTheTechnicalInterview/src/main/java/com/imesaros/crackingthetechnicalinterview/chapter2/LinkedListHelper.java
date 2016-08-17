package com.imesaros.crackingthetechnicalinterview.chapter2;

import java.util.HashMap;
import java.util.Map;

public class LinkedListHelper
{
    public static void removeDuplicatesWithBuffer(LinkedListNode head)
    {
        Map<Integer, Boolean> table = new HashMap<>();

        LinkedListNode previous = null;
        while(head != null)
        {
            if (table.get(head.getItem()) != Boolean.TRUE){
                table.put(head.getItem(), Boolean.TRUE);
                previous = head;
            }
            else{
                previous.setNext(head.getNext());
            }

            head = head.getNext();
        }
    }

    public static void removeDuplicatesWithoutBuffer(LinkedListNode head)
    {
        if (head == null | head.getNext() == null)
        {
            return;
        }
        LinkedListNode previous = head;
        LinkedListNode node = head.getNext();
        while (node != null)
        {
            LinkedListNode found = head;
            while(found != null && found.getItem() != node.getItem())
            {
                found = found.getNext();
            }
            if (found == node)
            {
                previous = node;
            }
            else{
                previous.setNext(node.getNext());
            }
            node = node.getNext();
        }
    }
}
