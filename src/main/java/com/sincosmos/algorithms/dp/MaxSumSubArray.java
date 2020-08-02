package com.sincosmos.algorithms.dp;

/**
 * 题目：输入一个整型数组，数组里有正数也有负数。
 * 数组中一个或连续的多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为 O(n)。
 * 例如
 *     输入的数组为{1, -2, 3, 10, -4, 7, 2, -5}，和最大的连续子数组为｛3, 10, -4, 7, 2}。
 *     因此输出为该连续子数组的和 18 。
 *
 * 解析
 *     假设以 arr[i] 结尾的的最大和连续子数组和为 f(i), 则以 arr[i+1] 结尾的最大和连续子数组和为
 *     max(f(i) + arr[i+1], arr[i+1])
 */
public class MaxSumSubArray {
    public int maxSumSubArray(int[] nums){
        int[] maxSum = new int[nums.length];
        maxSum[0] = nums[0];
        for(int i=1; i<nums.length; ++i){
            maxSum[i] = Math.max(maxSum[i-1] + nums[i], nums[i]);
        }

        int max = maxSum[0];
        for(int i=1; i<maxSum.length; ++i){
            if(maxSum[i] > max)
                max = maxSum[i];
        }

        return max;
    }


}
