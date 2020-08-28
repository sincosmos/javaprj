package com.sincosmos.algorithms.leetcode.dp;

public class P322CoinChange {
    //dp
    public int coinChange(int[] coins, int amount) {
        //组成 i 元所需最少硬币数
        int[] dp = new int[amount+1];
        //dp[0]=0
        for(int i=1; i<=amount; ++i){
            int min = Integer.MAX_VALUE;
            for(int j=0; j<coins.length; ++j){
                if(i-coins[j] >= 0 && dp[i-coins[j]] != -1){
                    min = Math.min(min, dp[i-coins[j]] + 1);
                }
            }
            if(min != Integer.MAX_VALUE)
                dp[i] = min;
            else dp[i] = -1;
        }
        return dp[amount];
    }


    //dfs
    public int coinChange2(int[] coins, int amount) {
        return dfs(0, coins, amount);
    }
    private int dfs(int idx, int[] coins, int amount){
        if(amount == 0){
            //需要 0 枚当前硬币
            return 0;
        }
        if(idx<coins.length && amount > 0){
            //当前硬币的最大数量
            int maxCount = amount/coins[idx];
            int minCost = Integer.MAX_VALUE;
            for(int x=0; x<=maxCount; ++x){
                int res = dfs(idx+1, coins, amount - x*coins[idx]);
                if(res != -1){
                    //当前 x 枚 coins[idx] 凑出了当前 amount
                    minCost = Math.min(minCost, res + x);
                }
            }
            //x 枚当前硬币使得后续使用 res 枚其它硬币凑出 amount，使得 x+res 最小
            return (minCost == Integer.MAX_VALUE)? -1: minCost;
        }
        //如果后续没有硬币或剩余金额为负值，表明不能凑出当前 amount
        return -1;
    }
}
