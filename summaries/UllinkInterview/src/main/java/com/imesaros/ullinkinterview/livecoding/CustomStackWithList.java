package com.imesaros.ullinkinterview.livecoding;

/**
 * Custom Stack:

 Design a stack that supports the following operations:
 - push <number> --> pushes a number on top of the stack
 - pop --> returns the number removed from the top of the stack
 - inc <number_of_elements> <increment> --> increment the last <number_of_elements>(the numbers at the bottom of the stack) by <increment>
 */
public class CustomStackWithList implements CustomStack
{
    private NodeList values = null;

    @Override
    public void push (Integer n){
        if (values == null){
            values = new NodeList(n, null);
        }
        else{
            NodeList newNode = new NodeList(n, values);
            values = newNode;
        }
    }

    @Override
    public Integer pop(){
        if (values == null){
            return null;
        }
        Integer value = values.value;
        values = values.next;
        return value;
    }

    @Override
    public void inc(Integer nrOfElement, Integer increment){
        NodeList nodeList = values;
        int count = 0;
        while(count< nrOfElement && nodeList != null){
            nodeList = nodeList.next;
            count ++;
        }
        if (nodeList != null){
            nodeList.value += increment;
        }
    }

    private class NodeList{
        private Integer value;
        private NodeList next;

        private NodeList(Integer value, NodeList next){
            this.value = value;
            this.next = next;
        }
    }

}
