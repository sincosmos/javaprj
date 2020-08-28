package com.sincosmos.algorithms.leetcode;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P79MatrixPath {
    public boolean exist(char[][] board, String word) {
        if(word == null) return false;
        if(word.length() == 0) return true;

        int rows = board.length, cols = 0;
        if(!(rows == 0))  cols = board[0].length;
        if(word.length() > rows*cols) return false;

        for(Pair<Integer, Integer> firstLocation : firstLocation(board, word.charAt(0))){
            Set<Pair<Integer, Integer>> visited = new HashSet<>();
            visited.add(firstLocation);
            if(exists(board, visited, firstLocation, word.substring(1))){
                return true;
            }
        }
        return false;
    }

    public boolean exists(char[][] board, Set<Pair<Integer, Integer>> visited, Pair<Integer, Integer> cur, String remaining){
        if(remaining.length() == 0) return true;
        boolean upMatched = false;
        Pair<Integer, Integer> up = new Pair<>(cur.getKey()-1, cur.getValue());
        if(!visited.contains(up)){
            if(insideAndEquals(board, up, remaining.charAt(0))){
                visited.add(up);
                upMatched = exists(board, visited, up, remaining.substring(1));
            }
        }
        boolean downMatched = false;
        Pair<Integer, Integer> down = new Pair<>(cur.getKey()+1, cur.getValue());
        if(!visited.contains(down)){
            if(insideAndEquals(board, down, remaining.charAt(0))){
                visited.add(down);
                downMatched = exists(board, visited, down, remaining.substring(1));
            }
        }
        boolean leftMatched = false;
        Pair<Integer, Integer> left = new Pair<>(cur.getKey(), cur.getValue()-1);
        if(!visited.contains(left)){
            if(insideAndEquals(board, left, remaining.charAt(0))){
                visited.add(left);
                leftMatched = exists(board, visited, left, remaining.substring(1));
            }
        }
        boolean rightMatched = false;
        Pair<Integer, Integer> right = new Pair<>(cur.getKey(), cur.getValue()+1);
        if(!visited.contains(right)){
            if(insideAndEquals(board, right, remaining.charAt(0))){
                visited.add(right);
                rightMatched = exists(board, visited, right, remaining.substring(1));
            }
        }
        return upMatched || downMatched || leftMatched || rightMatched;
    }

    private List<Pair<Integer, Integer>> firstLocation(char[][] board, char first){
        List<Pair<Integer, Integer>> firstMatch = new ArrayList<>();
        for(int row=0; row<board.length; ++row){
            for(int col=0; col<board[0].length; ++col){
                if(board[row][col] == first){
                    firstMatch.add(new Pair<>(row, col));
                }
            }
        }
        return firstMatch;
    }

    private boolean insideAndEquals(char[][] board, Pair<Integer, Integer> t, char next){
        int row = t.getKey();
        int col = t.getValue();
        if(row >=0 && row < board.length
                && col >=0 && col < board[0].length){
            if(board[row][col] == next)
                return true;
        }
        return false;
    }


    public static void main(String[] args){
        //[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
        //"ABCCED"
        //String word = "ABCCED";
        //char[][] board ={{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};

        //[["A","B","C","E"],["S","F","E","S"],["A","D","E","E"]]
        //"ABCESEEEFS"
        //  ['A','B','C','E'],
        //  ['S','F','E','S'],
        //  ['A','D','E','E']

        String word = "ABCESEEEFS";
        char[][] board ={{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        P79MatrixPath matrixPath = new P79MatrixPath();
        System.out.println(matrixPath.exist(board, word));
    }
}
