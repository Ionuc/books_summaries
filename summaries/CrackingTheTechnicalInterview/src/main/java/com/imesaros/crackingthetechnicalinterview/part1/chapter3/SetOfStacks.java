package com.imesaros.crackingthetechnicalinterview.part1.chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem 3.3
 * Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
 * Therefore, in real life, we would likely start a new stack when the previous stack exceeds
 * some threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks
 * should be composed of several stacks, and should create a new stack once
 * the previous one exceeds capacity. SetOfStacks.push() and SetOfStacks.pop() should
 * behave identically to a single stack (that is, pop() should return the same values as it
 * would if there were just a single stack).
 * FOLLOW UP
 * Implement a function popAt(int index) which performs a pop operation on a specific
 * sub-stack.
 */
public class SetOfStacks
{
    private final int capacity;
    List<Stack> stacks = new ArrayList<>();

    public SetOfStacks(int capacity)
    {
        this.capacity = capacity;
    }

    public void push(Integer value)
    {
        if (stacks.isEmpty())
        {
            addNewStackWithValue(value);
            return;
        }

        boolean valueWasAdded = stacks.get(stacks.size() - 1).push(value);
        if (!valueWasAdded)
        {
            addNewStackWithValue(value);
        }
    }

    private void addNewStackWithValue(Integer value)
    {
        Stack stack = new Stack(capacity);
        stack.push(value);
        stacks.add(stack);
    }

    public Integer pop()
    {
        if (stacks.isEmpty())
        {
            return null;
        }
        Integer value = stacks.get(stacks.size() - 1).pop();
        if (value != null)
        {
            checkForLastStack();
        }
        return value;
    }

    private void checkForLastStack()
    {
        Stack stack = stacks.get(stacks.size() - 1);
        Integer value = stack.pop();
        if (value == null)
        {
            stacks.remove(stacks.size() - 1);
        }
        else
        {
            stack.push(value);
        }
    }

    private class Stack
    {
        private final int capacity;
        private int                     totalValues = 0;
        private StackWithLinkedListNode stack       = new StackWithLinkedListNode();

        private Stack(int capacity)
        {
            this.capacity = capacity;
        }

        private boolean push(int value)
        {
            if (totalValues >= capacity)
            {
                return false;
            }
            stack.push(value);
            totalValues++;
            return true;
        }

        private Integer pop()
        {
            Integer value = stack.pop();
            if (value != null)
            {
                totalValues--;
            }
            return value;
        }

    }

}


