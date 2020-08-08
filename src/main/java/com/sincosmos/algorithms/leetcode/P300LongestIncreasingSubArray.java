package com.sincosmos.algorithms.leetcode;

public class P300LongestIncreasingSubArray {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 0;
        for(int i=1; i<nums.length; ++i){
            max = 0;
            for(int j=0; j<i; j++){
                if(nums[i] > nums[j] && max < dp[j]){
                    max = dp[j];
                }
            }
            dp[i] = max + 1;
        }

        max = 0;
        for(int i=0; i<dp.length; ++i){
            if(max<dp[i]){
                max = dp[i];
            }
        }
        return max;
    }
}

