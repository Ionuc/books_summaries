package com.imesaros.ullinkinterview.livecoding

import spock.lang.Specification

class AppleStockSpec extends Specification{

    AppleStock appleStock = new AppleStock()

    def 'should get getMaxProfit'(){
       expect:
       appleStock.getMaxProfit(stockPricesYesterday as int[]) == expected

        where:
        stockPricesYesterday   | expected
        null                   | 0
        []                     | 0
        [1]                    | 0
        [1,2]                  | 1
        [10, 7, 5, 8, 11, 9]   | 6
        [6,7, 10, 4, 12, 5,11] | 8
        [4,3,2,1]              | 0
    }
}
