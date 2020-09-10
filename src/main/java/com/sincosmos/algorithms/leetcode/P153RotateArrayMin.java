package com.sincosmos.algorithms.leetcode;

public class P153RotateArrayMin {
    public int findMin(int[] nums) {
        if(nums.length == 0) return -1;
        if(nums.length == 1) return nums[0];

        return findMin(nums, 0, nums.length-1);
    }

    private int findMin(int[] nums, int st, int ed){
        if(ed - st == 1) return Math.min(nums[st], nums[ed]);
        else if(st == ed) return nums[st];
        int md = st + (ed-st)/2;

        if(nums[md] < nums[st]){
            if(nums[md] < nums[ed]){
                //md 在后半区
                //向前半区继续搜索
                return findMin(nums, st, md);
            }else{
                //全数组单调递减
                return nums[ed];
            }

        }else{
            if(nums[md] > nums[ed]){
                //md 在前半区
                //向后半区搜索
                return findMin(nums, md, ed);
            }else{
                //全数组单调递增
                return nums[st];
            }
        }
    }
}
