package com.imesaros.crackingthetechnicalinterview.part1.chapter2;

import java.util.HashMap;
import java.util.Map;

public class LinkedListHelper
{
    public static void removeDuplicatesWithBuffer(LinkedListNode head)
    {
        Map<Integer, Boolean> table = new HashMap<>();

        LinkedListNode previous = null;
        while (head != null)
        {
            if (table.get(head.getItem()) != Boolean.TRUE)
            {
                table.put(head.getItem(), Boolean.TRUE);
                previous = head;
            }
            else
            {
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
            while (found != null && found.getItem() != node.getItem())
            {
                found = found.getNext();
            }
            if (found == node)
            {
                previous = node;
            }
            else
            {
                previous.setNext(node.getNext());
            }
            node = node.getNext();
        }
    }

    public static LinkedListNode add(LinkedListNode root1, LinkedListNode root2)
    {
        if (root1 == null)
        {
            return root2;
        }
        if (root2 == null)
        {
            return root1;
        }

        LinkedListNode rootResult = new LinkedListNode((root1.getItem() + root2.getItem()) % 10);
        int carry = (root1.getItem() + root2.getItem()) / 10;
        LinkedListNode root = rootResult;
        root1 = root1.getNext();
        root2 = root2.getNext();
        while (root1 != null && root2 != null)
        {
            LinkedListNode node = new LinkedListNode((root1.getItem() + root2.getItem() + carry) % 10);
            carry = (root1.getItem() + root2.getItem() + carry) / 10;
            root.setNext(node);
            root1 = root1.getNext();
            root2 = root2.getNext();
            root = root.getNext();
        }
        if (root1 != null || root2 != null)
        {
            addRemainingElements(root, root1, carry);
            addRemainingElements(root, root2, carry);
        }
        else if (carry > 0)
        {
            LinkedListNode node = new LinkedListNode(carry);
            root.setNext(node);
        }

        return rootResult;
    }

    private static void addRemainingElements(LinkedListNode root, LinkedListNode remaining, int carry)
    {
        if (remaining == null)
        {
            return;
        }
        while (remaining != null)
        {
            LinkedListNode node = new LinkedListNode((remaining.getItem() + carry) % 10);
            carry = (remaining.getItem() + carry) / 10;
            root.setNext(node);
            root = root.getNext();
            remaining = remaining.getNext();
        }
        if (carry > 0)
        {
            LinkedListNode node = new LinkedListNode(carry);
            root.setNext(node);
        }
    }
}
