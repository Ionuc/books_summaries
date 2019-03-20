package com.imesaros.geeksforgeeks

import spock.lang.Specification


class CoinChangeSpec extends Specification{

    def 'should test coins for'(){
        given:
        def coins = [1,2,3]
        def n = 4

        when:
        def result = CoinChange.count(coins, n)

        then:
        result == 4
    }
}
