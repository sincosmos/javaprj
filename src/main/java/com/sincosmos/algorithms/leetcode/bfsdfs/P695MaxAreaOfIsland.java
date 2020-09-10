package com.sincosmos.algorithms.leetcode.bfsdfs;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

public class P695MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length==0) return 0;
        int[][] visited = new int[grid.length][grid[0].length];
        int max = 0;
        for(int i=0; i<grid.length; ++i){
            for(int j=0; j<grid[0].length; ++j){
                if(grid[i][j] == 1 && visited[i][j] == 0 ){
                    max = Math.max(max, bfs(grid, new Pair<>(i,j), visited));
                }
            }
        }
        return max;
    }

    private int bfs(int[][] grid, Pair<Integer, Integer> st, int[][] visited){
        int islandArea = 0;
        Deque<Pair<Integer, Integer>> deque = new ArrayDeque<>();
        deque.push(st);
        visited[st.getKey()][st.getValue()] = 1;

        while(!deque.isEmpty()){
            int curSize = deque.size();
            islandArea+=curSize;
            for(int i=0; i<curSize; ++i){
                Pair<Integer, Integer> cur = deque.pollFirst();
                Pair<Integer, Integer> down = new Pair<>(cur.getKey()+1, cur.getValue());
                if(isNewIsland(grid, down, visited)){
                    visited[down.getKey()][down.getValue()] = 1;
                    deque.push(down);
                }

                Pair<Integer, Integer> right = new Pair<>(cur.getKey(), cur.getValue()+1);
                if(isNewIsland(grid, right, visited)){
                    visited[right.getKey()][right.getValue()] = 1;
                    deque.push(right);
                }

                Pair<Integer, Integer> up = new Pair<>(cur.getKey()-1, cur.getValue());
                if(isNewIsland(grid, up, visited)){
                    visited[up.getKey()][up.getValue()] = 1;
                    deque.push(up);
                }

                Pair<Integer, Integer> left = new Pair<>(cur.getKey(), cur.getValue()-1);
                if(isNewIsland(grid, left, visited)){
                    visited[left.getKey()][left.getValue()] = 1;
                    deque.push(left);
                }
            }
        }

        return islandArea;
    }

    private boolean isNewIsland(int[][] grid, Pair<Integer, Integer> cur, int[][] visited){
        int row = cur.getKey(), col = cur.getValue();
        return row >=0 && row < grid.length
                && col >=0 && col < grid[0].length
                && visited[row][col] == 0
                && grid[row][col] == 1;
    }


    public static void main(String[] args){
        //int[][] grid = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        int[][] grid = {{0,0,1},{1,1,1}};
        System.out.println(new P695MaxAreaOfIsland().maxAreaOfIsland(grid));
    }
}
