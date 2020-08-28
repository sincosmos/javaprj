package com.sincosmos.algorithms.swordoffer;

import java.util.HashSet;
import java.util.Set;

public class P03RepeatNumber {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; ++i){
            if(set.contains(nums[i])) return nums[i];
            else set.add(nums[i]);
        }
        return -1;
    }

    public int findRepeatNumber2(int[] nums) {
        for(int i=0; i<nums.length; ++i){
            while(i != nums[i]){
                if(nums[i] == nums[nums[i]]){
                    return nums[i];
                }
                int tmp = nums[i];
                nums[i] = nums[tmp];
                nums[tmp] = tmp;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        P03RepeatNumber r = new P03RepeatNumber();
        //int[] nums = {2, 3, 1, 0, 2, 5, 3};
        int[] nums = {0,2,4,5,5,4};
        System.out.println(r.findRepeatNumber2(nums));
    }
}
