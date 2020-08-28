package com.sincosmos.algorithms.swordoffer;

public class P15HammingWeight {
    public int hammingWeight(int n) {
        int cnt = 0;
        while(n != 0){
            if( (n & 1) == 1) cnt+=1;
            n = n >>> 1;
        }
        return cnt;
    }
}
