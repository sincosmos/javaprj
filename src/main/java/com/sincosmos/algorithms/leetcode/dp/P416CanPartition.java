package com.sincosmos.algorithms.leetcode.dp;


/**
 * 0-1 背包问题
 * @see P39CombinationSum 完全背包
 */
public class P416CanPartition {
    public boolean canPartition(int[] nums) {
        int sum=0;
        for(int i=0; i<nums.length; ++i){
            sum+=nums[i];
        }
        if((sum & 1) == 1) return false;
        int targetSum = sum / 2;

        boolean[][] dp = new boolean[nums.length+1][targetSum+1];
        dp[0][0] = true;
        for(int i=1; i<=nums.length; ++i){
            for(int j=0; j<=targetSum; ++j){
                if(j-nums[i-1]>=0){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[nums.length][targetSum];
    }

    public boolean canPartition1(int[] nums){
        if (nums.length == 1 && nums[0] != 0)
            return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 !=0)
            return false;
        int target = sum / 2;
        int[][] dp = new int[nums.length+1][target+1];
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (j >= nums[i-1])
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j - nums[i-1]] + nums[i-1]);
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[nums.length][target] == target;
    }


    public boolean canPartition2(int[] nums) {
        int sum=0;
        for(int i=0; i<nums.length; ++i){
            sum+=nums[i];
        }
        if((sum & 1) == 1) return false;
        int targetSum = sum / 2;

        boolean[] dp = new boolean[targetSum+1];
        //用前一个数字组成 j 的可能性初始化
        //前一个数字组成 0，不选它即可
        dp[0] = true;
        if(nums[0]<=targetSum){
            //前一个数字只能组成它自身
            dp[nums[0]] = true;
        }else{
            //如果某个数字大于 targetSum，其它数字的和肯定小于 targetSum
            return false;
        }

        //前 i+1 个数字组成 j 的可能性
        for(int i=1; i<nums.length; ++i){
            for(int j=targetSum; j>=nums[i]; j--){
                if(dp[targetSum]){
                    return true;
                }
                dp[j] = dp[j] || dp[j-nums[i]];
            }
        }

        return dp[targetSum];
    }

    public static void main(String[] args){
        P416CanPartition canPartition = new P416CanPartition();
        int[] nums = {1,2,5};
        System.out.println(canPartition.canPartition2(nums));
    }
}
