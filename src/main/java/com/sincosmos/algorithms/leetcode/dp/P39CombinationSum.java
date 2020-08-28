package com.sincosmos.algorithms.leetcode.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 完全背包问题
 * @see P416CanPartition 0-1 背包
 */
public class P39CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();

        //前 j 个元素和为 0...target 的所有组合
        List<List<Integer>>[] pre = new ArrayList[target+1];
        //前 j+1 个元素和为 0...target 的所有组合
        List<List<Integer>>[] cur = new ArrayList[target+1];

        for(int i=0; i<=target; ++i){
            //和为 i 的组合
            Set<Integer> sumI = new TreeSet<>();
            for(int j=0; j<candidates.length; ++j){
                if(candidates[j] > target) {
                    cur = pre;
                }else{

                }
            }
        }

        return cur[target];
    }
}
