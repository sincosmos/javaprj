package com.sincosmos.algorithms.dp;

import java.util.stream.IntStream;

public class DpTests {
    public static void testFibonacci(int n){
        Fibonacci dp = new Fibonacci();
        System.out.println("Fibonacci of " + n);
        System.out.println(dp.fibonacci(n));
    }

    public static void testCutRod(int[] price, int ncut){
        CutRod dp = new CutRod();
        System.out.println(ncut + "::" + dp.cutRod(price, ncut));
    }

    public static void main(String[] args){
        IntStream.range(0,10).forEach(DpTests::testFibonacci);
        //cut rod
        int[] price = {1,5,8,9,10,17,17,20,24,30};
        //DpTests.testCutRod(price, 5);
    }
}
