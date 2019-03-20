package com.imesaros.geeksforgeeks;

import java.util.List;

public class CoinChange {
    public static int count(List<Integer> coins, int total)
    {
        return count(coins, coins.size() , total);
    }

    private static int count (List<Integer> coins, int index, int current)
    {
        if (current == 0)
        {
            return 1;
        }
        if (current < 0)
        {
            return 0;
        }
        if (index <=0)
        {
            return 0;
        }

        return count(coins, index -1, current)
                + count(coins, index, current - coins.get(index-1));
    }
}
