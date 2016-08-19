package com.imesaros.crackingthetechnicalinterview.part1.chapter3;

/**
 * Problem 3.5
 * Implement a MyQueue class which implements a queue using two stacks.
 */
public class MyQueue {
    private final StackWithLinkedListNode stack1 = new StackWithLinkedListNode();
    private final StackWithLinkedListNode stack2 = new StackWithLinkedListNode();

    private int nrOfElements = 0;

    public void enqueue(int value) {
        stack1.push(value);
        stack2.push(value);
        nrOfElements++;
    }

    public Integer dequeue() {

        if (nrOfElements == 0) {
            return null;
        }

        Integer result = getLastElement(stack1, stack2);
        getLastElement(stack2, stack1);

        nrOfElements--;
        return result;
    }

    private Integer getLastElement(StackWithLinkedListNode stackFrom, StackWithLinkedListNode auxiliaryStack) {
        for (int i = 1; i < nrOfElements; i++) {
            auxiliaryStack.push(stackFrom.pop());
        }
        Integer result = stackFrom.pop();
        for (int i = 1; i < nrOfElements; i++) {
            stackFrom.push(auxiliaryStack.pop());
        }
        return result;
    }
}
