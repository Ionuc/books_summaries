package com.imesaros.crackingthetechnicalinterview.part1.chapter2;

import java.util.HashMap;
import java.util.Map;

public class LinkedListHelper
{

    public void appendToTail(LinkedListNode head, int d)
    {
        LinkedListNode node = new LinkedListNode(d);
        LinkedListNode h = head;
        while (h.getNext() != null)
        {
            h = h.getNext();
        }
        h.setNext(node);
    }

    public LinkedListNode remove(LinkedListNode head, int i)
    {
        if (head == null)
        {
            return null;
        }
        while (head.getNext() != null && head.getItem() != i)
        {
            head = head.getNext();
        }
        return head;
    }

    /**
     * Write code to remove duplicates from an unsorted linked list.
     * FOLLOW UP
     * How would you solve this problem if a temporary buffer is not allowed?
     *
     * @param head
     */
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
        if (head == null || head.getNext() == null)
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

    /**
     * You have two numbers represented by a linked list, where each node contains a single digit.
     * The digits are stored in reverse order, such that the 1’s digit is at the head of the list.
     * Write a function that adds the two numbers and returns the sum as a linked list.
     * EXAMPLE
     * Input: (3 -> 1 -> 5) + (5 -> 9 -> 2)
     * Output: 8 -> 0 -> 8
     *
     * @param root1
     * @param root2
     * @return
     */
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

    /**
     * Given a circular linked list, implement an algorithm which returns node at the beginning of the loop.
     * DEFINITION
     * Circular linked list: A (corrupt) linked list in which a node’s next pointer points to an
     * earlier node, so as to make a loop in the linked list.
     * EXAMPLE
     * input: A -> B -> C -> D -> E -> C [the same C as earlier]
     * output: C
     *
     * @param root
     * @return
     */
    public static LinkedListNode findFirstLoop(LinkedListNode root)
    {
        if (root == null || root.getNext() == null)
        {
            return null;
        }

        LinkedListNode node = root.getNext();
        int currentPosition = 1;
        while (node != null)
        {
            int foundedPosition = 0;
            LinkedListNode found = root;
            while (found != null && found != node)
            {
                found = found.getNext();
                foundedPosition++;
            }
            if (currentPosition != foundedPosition)
            {
                return node;
            }
            node = node.getNext();
            currentPosition++;
        }
        return null;
    }
}
