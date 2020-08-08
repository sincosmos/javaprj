package com.sincosmos.algorithms.leetcode;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

public class P279NumSquares {
    public int numSquares(int n) {
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(n, 1));
        boolean[]  visited = new boolean[n+1];

        while(!queue.isEmpty()){
            Pair<Integer, Integer> first = queue.poll();
            n = first.getKey();
            int step = first.getValue();
            if(n==0 || n - (int) Math.sqrt(n) * (int) Math.sqrt(n) == 0){
                return step;
            }
            for(int i= (int) Math.sqrt(n); n-i*i>=0 && i>=0; i--){
                if(!visited[n-i*i]){
                    visited[n-i*i] = true;
                    queue.offer(new Pair<>(n-i*i, step + 1));
                }
            }
        }

        return -1;
    }

    public int numSquares2(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int i=1; i<=n; i++){
            for(int j=1; j*j <= i; j++){
                int tmp = dp[i - j * j] + 1;
                if(dp[i] == 0){
                    dp[i] = tmp;
                }else {
                    dp[i] = Math.min(dp[i], tmp);
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args){
        System.out.println(new P279NumSquares().numSquares(12));
    }
}
