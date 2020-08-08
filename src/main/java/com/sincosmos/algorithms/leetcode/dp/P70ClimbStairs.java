package com.sincosmos.algorithms.leetcode.dp;

public class P70ClimbStairs {
    public int climbStairs(int n) {
        if(n<=0) return 0;
        int pre1 = 1;
        int pre2 = 2;
        int fn = 0;
        if(n == 1) return pre1;
        if(n == 2) return pre2;

        for(int i=3; i<=n; ++i){
            fn = pre1 + pre2;
            pre1 = pre2;
            pre2 = fn;
        }
        return fn;
    }
}
