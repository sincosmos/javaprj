package com.sincosmos.algorithms.leetcode.bfsdfs;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

/*
* 定义一个二维数组：
* int maze[5][5] = {
*    0, 1, 0, 0, 0,
*    0, 1, 0, 1, 0,
*    0, 0, 0, 0, 0,
*    0, 1, 1, 1, 0,
*    0, 0, 0, 1, 0,
* };
* 它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走。
* 编程序找出从左上角到右下角的最短路线。
* */
public class Maze {
    private int[][] maze = {
            {0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 1, 0}
        };

    public int shortestPath(){
        Deque<Pair<Integer, Integer>> deque = new ArrayDeque<>();
        deque.add(new Pair<>(0,0));
        int depth=0;
        boolean canPass = false;
        int[][] visited = new int[5][5];
        Pair<Integer, Integer> end = new Pair<>(4, 4);
        while(!deque.isEmpty()){
            depth++;
            int curSize = deque.size();
            for(int i=0;i<curSize; ++i){
                Pair<Integer, Integer> cur = deque.pollFirst();
                visited[cur.getKey()][cur.getValue()] = 1;
                if(end.equals(cur)) {
                    canPass = true;
                    break;
                }
                if(isPath(cur, 1, visited)) deque.add(new Pair(cur.getKey() - 1, cur.getValue()));
                if(isPath(cur, 2, visited)) deque.add(new Pair(cur.getKey() + 1, cur.getValue()));
                if(isPath(cur, 3, visited)) deque.add(new Pair(cur.getKey(), cur.getValue() - 1));
                if(isPath(cur, 4, visited)) deque.add(new Pair(cur.getKey(), cur.getValue() + 1));
            }
        }
        if(canPass)
            return depth;
        else return -1;
    }

    /**
     * @param cur
     * @param direction 0: go right, 1: go down
     * @return
     */
    private boolean isPath(Pair<Integer, Integer> cur, int direction, int[][] visited){
        int rowIndex = cur.getKey();
        int colIndex = cur.getValue();
        if(direction == 1 && rowIndex - 1 >= 0 && visited[rowIndex - 1][colIndex] == 0){
            //go up
            return maze[rowIndex - 1][colIndex] == 0;
        }
        if(direction == 2 && rowIndex + 1 < maze.length && visited[rowIndex + 1][colIndex] == 0){
            //go down
            return maze[rowIndex + 1][colIndex] == 0;
        }
        if(direction == 3 && colIndex -1 >= 0 && visited[rowIndex][colIndex - 1] == 0){
            //go left
            return maze[rowIndex][colIndex - 1] == 0;
        }
        if(direction == 4 && colIndex + 1 < maze[0].length && visited[rowIndex][colIndex + 1] == 0){
            //go right
            return maze[rowIndex][colIndex + 1] == 0;
        }

        return false;
    }

    public static void main(String[] args){
        Maze maze = new Maze();
        System.out.println(maze.shortestPath());
    }
}
