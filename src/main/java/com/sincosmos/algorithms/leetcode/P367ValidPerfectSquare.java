package com.sincosmos.algorithms.leetcode;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 *
 * Note: Do not use any built-in library function such as sqrt.
 *
 * Example 1:
 *
 * Input: 16
 * Output: true
 *
 * Example 2:
 *
 * Input: 14
 * Output: false
 */
public class P367ValidPerfectSquare {
    public static boolean isPerfectSquare(int num) {
        //2147483647
        int i=1;
        while(i<=num/i){
            if(i*i == num) return true;
            else i++;
        }
        return false;
    }

    public static void main(String[] args){
        System.out.println(isPerfectSquare(2147483647));
    }
}
