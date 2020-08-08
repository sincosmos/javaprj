package com.sincosmos.algorithms.dp;

public class CoinComposite {
    private int[] coins = {1,5,10,20};
    public long spiltSum(int x){
        long[][] dp = new long[coins.length+1][x+1];

        for(int i=0; i<=coins.length; ++i){
            //前 i 种币值组成和为 0 的方式有 1 种，即每种币值的个数都为 0
            dp[i][0] = 1;
        }

        //dp[i][j] 是前 i 种币值，组成和为 j 的方式
        for(int i=1; i<=coins.length; ++i){
            for(int j=1; j<=x; j++){
                for(int k=0; k<=j/coins[i-1]; ++k){
                    dp[i][j] += dp[i-1][j - k*coins[i-1]];
                }
            }
        }

        return dp[coins.length-1][x];
    }

    public static void main(String[] args){
        System.out.println(new CoinComposite().spiltSum(27));
    }
}
