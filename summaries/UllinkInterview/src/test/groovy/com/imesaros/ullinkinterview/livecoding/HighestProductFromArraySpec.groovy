package com.imesaros.ullinkinterview.livecoding

import spock.lang.Specification


class HighestProductFromArraySpec extends Specification{

    HighestProductFromArray highestProductFromArray = new HighestProductFromArray()

    def 'should get the highest product from array'(){
        expect:
        highestProductFromArray.highestProduct(values as Integer[], k) == expected

        where:
        values | k | expected
        [1,2,3,4,5] | 3 | 60
        [5,4,3,2,1] | 3 | 60
        [-2,-1,0,1,2] | 3 | 0
        [-2,-1,0,1,2] | 2 | 2
    }
}
