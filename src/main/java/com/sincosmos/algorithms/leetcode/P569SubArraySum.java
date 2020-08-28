package com.sincosmos.algorithms.leetcode;

public class P569SubArraySum {
    public int subarraySum(int[] nums, int k) {
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        for(int i=1; i<nums.length; ++i){
            preSum[i] = preSum[i-1] + nums[i];
        }

        int cnt = 0;
        for(int i=0; i<preSum.length; ++i){
            if(preSum[i] == k) cnt++;
            for(int j=0; j<i; ++j){
                if(preSum[i] - preSum[j] == k) cnt++;
            }
        }
        return cnt;
    }
}
