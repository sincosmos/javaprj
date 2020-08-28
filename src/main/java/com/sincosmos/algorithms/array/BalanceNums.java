package com.sincosmos.algorithms.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 左边都比它小
 * 右边都比它大
 */
public class BalanceNums {
    public List<Integer> balanceNums(int[] arr){
        if(arr.length<=2) return Collections.emptyList();

        List balance = new ArrayList();

        int leftMax = arr[0];
        int rightMin = arr[2];
        int rightMinIndex = 2;

        for(int i=1; i<arr.length-1; ++i){
            if(arr[i] < leftMax) {
                //小于左侧最大值，不符合
                continue;
            }else{
                //左侧符合
                leftMax = arr[i];
            }
            if(i < rightMinIndex){
                //大于右侧最小值，不符合
                if(arr[i] > rightMin){
                    continue;
                }
            }else{
                rightMinIndex = i+1;
                rightMin = arr[i+1];
            }

            // go on with remaining right part
            for(int j=rightMinIndex; j<arr.length; ++j){
                if(arr[i] > arr[j]){
                    rightMinIndex = j;
                    rightMin = arr[j];
                    break;
                }
            }
            if(arr[i] <= rightMin){
                balance.add(arr[i]);
            }

        }
        return balance;
    }


    public static void main(String[] args){
        BalanceNums balance = new BalanceNums();
        int[] arr = {1,2,3,4,5,6};
        int[] arr2 = {3,2,5,8,6,10,12};
        System.out.println(balance.balanceNums(arr2).stream().map(String::valueOf).collect(Collectors.joining(",")));
    }
}
