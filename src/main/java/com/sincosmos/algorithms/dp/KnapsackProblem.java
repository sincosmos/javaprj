package com.sincosmos.algorithms.dp;

public class KnapsackProblem {
    //0-1背包
    public int knapsack(int[] itemWeight, int[] itemValue, int capacity){
        //从前 i 种物品种挑出容量为 j 的最大价值
        int[][] dp = new int[itemWeight.length+1][capacity+1];

        for(int i=1; i<=itemWeight.length; ++i){
          for(int j=1; j<=capacity; ++j){
              if(j-itemWeight[i] >= 0) {
                  dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - itemWeight[i]] + itemValue[i]);
              }else{
                  dp[i][j] = dp[i - 1][j];
              }
          }
        }
        return dp[itemWeight.length][capacity];
    }
}
