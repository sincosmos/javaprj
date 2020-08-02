package com.sincosmos.algorithms.leetcode;

/**
 * Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).
 */
public class P191HammingWeight {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for(int i=0;i<32;i++){
            if( ((n >> i) & 0x1) == 1){
                count++;
            }
        }
        return count;
    }
}
