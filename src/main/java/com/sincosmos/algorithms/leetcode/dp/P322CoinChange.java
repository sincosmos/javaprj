package com.sincosmos.algorithms.leetcode.dp;

public class P322CoinChange {
    public int coinChange(int[] coins, int amount) {
        //组成 i 所需最少硬币个数
        int[] dp = new int[amount + 1];
        //组成 0 所需为 0
        dp[0] = 0;
        int min = Integer.MAX_VALUE;
        for(int i=1; i<=amount; ++i){
            for(int j=0; j<i; ++j){
                // if exists j + c * coins[x] = i
                // min = Math.min(min, dp[j] + c)
            }
            //dp[i] = min;
        }
        for(int i=0; i<coins.length; ++i){
            //for()
        }
        return -1;
    }
}
