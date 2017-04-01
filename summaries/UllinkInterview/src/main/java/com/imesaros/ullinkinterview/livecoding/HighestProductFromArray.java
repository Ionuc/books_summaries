package com.imesaros.ullinkinterview.livecoding;

/**
 * Given a list of integers and a k, find the highest product you can get from k of the integers.
 */
public class HighestProductFromArray {


    public Integer highestProduct(Integer[] values, Integer k){
        if (values == null || values.length < k){
            throw new UnsupportedOperationException();
        }
        Integer[] max = new Integer[k];
        for (int i=0 ; i< values.length; i++){
            if (max[k-1] == null || (max[0] != null && max[0] < values[i])){
                insert(values[i], max);
            }
        }
        int product = 1;
        for (int i = 0 ; i < k ; i++){
            product *= max[i];
        }
        return product;
    }

    private void insert(Integer value, Integer[] max){
        if(max[max.length-1] == null){
            max[max.length-1] = value;
            for (int i=max.length -1 ; i> 0; i--){
                if (max[i-1] == null || max[i-1] > max[i]){
                    switchValues(max, i, i-1);
                }
            }
        }
        else{
            max[0] = value;
            for (int i=1 ; i<= max.length-1; i++){
                if (max[i-1] > max[i]){
                    switchValues(max, i, i-1);
                }
            }
        }
    }

    private void switchValues(Integer[] max, int i, int j){
        int aux = max[i];
        max[i] = max[j];
        max[j] = aux;
    }
}
