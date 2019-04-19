package com.sincosmos.algorithms.leetcode;

import java.util.HashMap;

import static it.unimi.dsi.fastutil.ints.IntArrays.swap;

/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 */
public class P169MajorityElement {

    public void quickSort(int[] src, int st, int ed){
        if(st >= ed) return;

        int key = src[ed];
        int i=st, j=ed;
        while(i<j){
            while(i<j){
                if(src[i] > key){
                    break;
                }
                i++;
            }
            while(i<j){
                if(src[j] < key){
                    break;
                }
                j--;
            }
            int tmp = src[i];
            src[i] = src[j];
            src[j]=tmp;
        }
        src[ed] = src[i];
        src[i] = key;
        quickSort(src, st, i-1);
        quickSort(src, i+1, ed);
    }

    public int majorityElement2(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums[nums.length/2];
    }

    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for(int num : nums){
            count.put(num, count.getOrDefault(num, 0) + 1);
            if(count.get(num) > nums.length/2) return num;
        }
        return -1;
    }
}
