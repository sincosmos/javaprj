package com.sincosmos.algorithms.dp;

/**
 * 最长上升子序列（LIS）问题：给定长度为n的序列a，从a中抽取出一个子序列，这个子序列需要单调递增。
 * 问最长的上升子序列（LIS）的长度。
 * 　　e.g. 1,5,3,4,6,9,7,8的LIS为1,3,4,6,7,8，长度为 6。
 * 解析：设序列为 {arr[0], arr[1],..., arr[n-1]}，记以元素 arr[k] (0<=k<n) 结尾的最长上升子序列的长度为
 *      dp(k), 那么以 arr[k+1] 结尾的最长上升子序列的长度是
 *      if 存在 0<=x<=k 使得 arr[k+1] > arr[x], dp(k+1) = max(dp[x]) + 1
 *      else dp[k+1] = 1 (即 arr[k+1] 本身构成以它结尾的最长上升子序列)
 */
public class MaxLengthIncreasingSubArray {
    public int maxLengthIncreasingSubArray(int[] arr){
        //dp[i] 是以 arr[i] 结尾的最长上升子序列的长度
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for(int i=1; i<arr.length; ++i){
            int maxj = 0;
            //找到 max(dp[x])
            for(int j=0; j<i; ++j){
                if(arr[i] >= arr[j]){
                    maxj = maxj < dp[j] ? dp[j] : maxj;
                }
            }
            dp[i] = maxj + 1;
        }

        int maxLen = 0;
        for(int i=0; i<dp.length; ++i){
            if(dp[i] > maxLen) maxLen = dp[i];
        }
        return maxLen;
    }
}
