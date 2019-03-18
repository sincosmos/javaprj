package com.sincosmos.algorithms.dp;

public class Fibonacci {
    public int fibonacci(int n){
        if(n<=0){
            return n;
        }
        int fibn1 = 0;
        int fibn2 = 1;
        int fibnn = 1;

        for(int i=2; i<=n; ++i){
            fibnn = fibn2 + fibn1;
            fibn1 = fibn2;
            fibn2 = fibnn;
        }
        return fibnn;
    }
}
