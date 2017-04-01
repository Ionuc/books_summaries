package com.imesaros.ullinkinterview.livecoding;

/**
 * Custom Stack:

 Design a stack that supports the following operations:
 - push <number> --> pushes a number on top of the stack
 - pop --> returns the number removed from the top of the stack
 - inc <number_of_elements> <increment> --> increment the last <number_of_elements>(the numbers at the bottom of the stack) by <increment>
 */
public class CustomStackWithArray implements CustomStack{

    private Integer[] values;
    private int nrOfValues = 0;

    public CustomStackWithArray(int initialSize){
        if (initialSize <= 0){
            throw new UnsupportedOperationException("Cannot create customStack with initial size negative");
        }
        values = new Integer[initialSize];
    }

    @Override
    public void push (Integer n){
        resizeIfNeeded();
        values[nrOfValues] = n;
        nrOfValues ++;
    }

    private void resizeIfNeeded(){
        if (values.length == nrOfValues){
            Integer[] newValues = new Integer[nrOfValues*2];
            for (int i = 0; i < nrOfValues ; i ++){
                newValues[i] = values[i];
            }
            values = newValues;
        }
    }

    @Override
    public Integer pop(){
        if (nrOfValues == 0){
            return null;
        }
        Integer value = values[nrOfValues-1];
        values[nrOfValues-1] = null;
        nrOfValues --;
        return value;
    }

    @Override
    public void inc(Integer nrOfElement, Integer increment){
        if (nrOfElement >= nrOfValues){
            return;
        }
        values[nrOfValues-nrOfElement-1] += increment;
    }
}
