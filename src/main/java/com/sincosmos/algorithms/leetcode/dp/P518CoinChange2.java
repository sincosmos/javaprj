package com.sincosmos.algorithms.leetcode.dp;

public class P518CoinChange2 {
    public int change(int amount, int[] coins) {

        //前 i 种硬币组成和为 j 的组合数
        int[][] dp = new int[coins.length+1][amount+1];

        //前i种硬币组成和为 0 的组合数都为 1
        for(int i=0; i<=coins.length; i++){
            dp[i][0] = 1;
        }

        for(int i=1; i<=coins.length; ++i){
            for(int j=1; j<=amount; ++j){
                if(coins[i-1]>j){
                    dp[i][j] = dp[i-1][j];
                }else{
                    //使用 x 枚 coins[i-1]
                    for(int x=0; x<=j/coins[i-1]; ++x){
                        dp[i][j] += dp[i-1][j-x*coins[i-1]];
                    }
                }
            }
        }

        return dp[coins.length][amount];
    }

    public int change2(int amount, int[] coins) {

        //前 i 种硬币组成和为 j 的组合数
        int[][] dp = new int[coins.length+1][amount+1];

        //前i种硬币组成和为 0 的组合数都为 1
        for(int i=0; i<=coins.length; i++){
            dp[i][0] = 1;
        }

        for(int i=1; i<=coins.length; ++i){
            for(int j=1; j<=amount; ++j){
                if(coins[i-1]>j){
                    dp[i][j] = dp[i-1][j];
                }else{
                    //注意由于 coins[i-1]可以重复使用
                    //前 i-1 种币种组成 j + 前 i 种币种 j-coins[i-1]
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                }
            }
        }

        return dp[coins.length][amount];
    }

    public static void main(String[] args){
        P518CoinChange2 coinChange = new P518CoinChange2();
        int[] coins = {1,2,5};
        System.out.println(coinChange.change(5, coins));
    }
}
