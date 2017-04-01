package com.imesaros.ullinkinterview.livecoding;

/**
 * Suppose we could access yesterday's stock prices as an array, where:

 The values are the price in dollars of Apple stock.
 A higher index indicates a later time.
 So if the stock cost $500 at 10:30am and $550 at 11:00am, then:

 stockPricesYesterday[60] = 500;

 Write an efficient function that takes stockPricesYesterday and returns the best profit I could have made from 1 purchase and 1 sale of 1 Apple stock yesterday.

 For example:

 int[] stockPricesYesterday = new int[]{10, 7, 5, 8, 11, 9};

 getMaxProfit(stockPricesYesterday);
 // returns 6 (buying for $5 and selling for $11)

 No "shorting"—you must buy before you sell. You may not buy and sell in the same time step (at least 1 minute must pass).

 Extra 1: What if the stock value goes down all day? In that case, the best profit will be negative.
 Extra 2: O(n)
 */
public class AppleStock {

    public int getMaxProfit(int[] stockPricesYesterday){
        if (stockPricesYesterday == null || stockPricesYesterday.length <= 1){
            return 0;
        }
        int buy = 0;
        int sell = 0;
        int potentialBuy = 0;

        for (int i = 1 ; i < stockPricesYesterday.length; i++){
            if (stockPricesYesterday[i] < stockPricesYesterday[potentialBuy]){
                potentialBuy = i;
            }
            if(stockPricesYesterday[i] > stockPricesYesterday[sell]){
                sell = i;
            }
            if (potentialBuy < sell){
                buy = potentialBuy;
            }
        }
        return stockPricesYesterday[sell] - stockPricesYesterday[buy];
    }
}
