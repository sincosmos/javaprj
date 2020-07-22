package com.sincosmos.collection;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PowerSetAgain {
    public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> rtn = new ArrayList<>();
        rtn.add(Collections.emptyList());
        for(int i=0; i<nums.length; ++i){
            List<List<Integer>> tmp = new ArrayList<>();
            for(List<Integer> a : rtn){
                List<Integer> arr = new ArrayList<>(a);
                arr.add(nums[i]);
                tmp.add(arr);
            }
            rtn.addAll(tmp);
        }
        return rtn;
    }


    public List<List<Integer>> powerSet(int[] nums){
        if(nums.length > 32){
            throw new IllegalArgumentException("Too many elements");
        }

        return new AbstractList<List<Integer>>() {
            @Override
            public List<Integer> get(int index) {
                List<Integer> result = new ArrayList<>();
                for(int i=0; index != 0; i++, index >>= 1){
                    if( (index & 1) == 1){
                        result.add(nums[i]);
                    }
                }
                return result;
            }

            @Override
            public int size() {
                return 1 << nums.length;
            }
        };
    }
}
