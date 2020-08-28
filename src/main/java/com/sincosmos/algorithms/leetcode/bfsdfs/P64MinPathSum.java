package com.sincosmos.algorithms.leetcode.bfsdfs;

/*
给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:

输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。
* */

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 *
 */
public class P64MinPathSum {

    public int minPathSum(int[][] grid) {
        int min = Integer.MAX_VALUE;
        return min;
    }

    //BFS
    private int bfs(int[][] grid){
        Deque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        queue.push(new Pair(0, 0));

        int rows = grid.length;
        int cols = grid[0].length;

        int min = Integer.MAX_VALUE;
        int pathSum = 0;
        while(!queue.isEmpty()){
            int curSize = queue.size();
            for(int i=0; i<curSize; ++i){
                Pair<Integer, Integer> cur = queue.pollFirst();
                int rowIndex = cur.getKey();
                int colIndex = cur.getValue();
                pathSum += grid[rowIndex][colIndex];
                //go left
                if(colIndex+1 < cols) queue.push(new Pair(rowIndex, colIndex+1));
                //go down
                if(rowIndex+1 < rows) queue.push(new Pair(rowIndex+1, colIndex));
            }
        }

        return min;
    }

    //dfs
    //recursive
    //可能超时
    private int recursive(int[][] grid, Pair<Integer, Integer> cur, Map<Pair<Integer, Integer>, Integer> remembered){
        if(remembered.get(cur) != null) return remembered.get(cur);

        int rowIndex = cur.getKey();
        int colIndex = cur.getValue();
        if(rowIndex >= grid.length || colIndex >= grid[0].length){
            return 0;
        }
        int minSum = -1;
        if(rowIndex + 1 == grid.length) {
            //can go left only
            minSum = grid[rowIndex][colIndex] + recursive(grid, new Pair<>(rowIndex, colIndex + 1), remembered);
        }
        if(colIndex + 1 == grid[0].length){
            //can go down only
            minSum =  grid[rowIndex][colIndex] + recursive(grid, new Pair<>(rowIndex+1, colIndex), remembered);
        }
        if(minSum == -1) {
            minSum = grid[rowIndex][colIndex] + Math.min(
                    recursive(grid, new Pair<>(rowIndex, colIndex + 1), remembered),
                    recursive(grid, new Pair<>(rowIndex + 1, colIndex), remembered)
            );
        }
        remembered.put(cur, minSum);
        return minSum;
    }


    private int dp(int[][] grid){
        int r = grid.length, c = grid[0].length;
        int[][] dp = new int[r][c];
        dp[0][0] = grid[0][0];
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(i==0 && j==0){
                    continue;
                }else if(i==0 && j>0){
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                }else if(j==0 && i>0){
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
            }
        }

        return dp[r-1][c-1];
    }
}
