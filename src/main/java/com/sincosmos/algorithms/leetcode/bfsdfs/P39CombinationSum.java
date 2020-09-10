package com.sincosmos.algorithms.leetcode.bfsdfs;

import com.sincosmos.algorithms.leetcode.dp.P416CanPartition;

import java.util.*;

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


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, 0, target, new ArrayDeque<>(), res);
        return res;
    }


    private void dfs(int[] candidates, int begin, int target, Deque<Integer> path, List<List<Integer>> res) {
        if(target < 0) return;
        if(target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=begin; i<candidates.length; ++i){
            path.add(candidates[i]);
            dfs(candidates, i, target - candidates[i], path, res);
            path.pollLast();
        }
    }
}
