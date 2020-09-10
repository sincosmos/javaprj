package com.sincosmos.algorithms.leetcode;

public class P154RotateArrayMin {
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length-1);
    }

    private int findMin(int[] nums, int st, int ed){
        if(st>=ed) return -1;
        int md = st + (ed-st)/2;

        if(md-1>=st){
            if(nums[md] < nums[md-1]){

            }
        }

        return -1;
    }
}
