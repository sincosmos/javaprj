package com.sincosmos.algorithms.leetcode;

public class P594LongestHarmonySubArray {
    public int findLHS(int[] nums) {
        if(nums.length <= 1) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 0;
        int max;
        for(int i=1; i<nums.length; ++i){
            max = 0;
            for(int j=0; j<i; j++){
                if(Math.abs(nums[i] - nums[j]) <= 1) {
                    //connected
                    if(dp[j] == 0) dp[j] = 1;
                    if(max < dp[j])
                        max = dp[j];
                }
            }
            if(max > 0)
                dp[i] = max + 1;
        }
        max = dp[0];
        for(int i=0; i<dp.length; ++i){
            if(max<dp[i]) max = dp[i];
        }
        return max;
    }
}
