package com.sincosmos.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/*
    Given a non-empty array of integers, every element appears twice except for one. Find that single one.

    Note:

    Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

    Example 1:

    Input: [2,2,1]
    Output: 1
    Example 2:

    Input: [4,1,2,1,2]
    Output: 4
* */
public class P136SingleNumber {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for(int num : nums){
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry: count.entrySet()){
            if(entry.getValue() == 1) return entry.getKey();
        }
        return -1;
    }

    public int singleNumber2(int[] nums) {
        TreeSet<Integer> count = new TreeSet<>();
        for(int num : nums){
            if(count.contains(num)) count.remove(num);
            else count.add(num);
        }

        return count.pollFirst();
    }
}
