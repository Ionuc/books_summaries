package com.imesaros.patterns.design.behavioral.chainofresponsibility;

public interface DispenseChain
{
    void setNextChain(DispenseChain nextChain);

    void dispense(Currency cur);
}
