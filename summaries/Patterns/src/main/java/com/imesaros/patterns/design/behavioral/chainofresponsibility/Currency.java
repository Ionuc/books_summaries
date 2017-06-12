package com.imesaros.patterns.design.behavioral.chainofresponsibility;

public class Currency
{
    private int amount;

    public Currency(int amt)
    {
        this.amount = amt;
    }

    public int getAmount()
    {
        return this.amount;
    }
}
