package com.sincosmos.effectivejava.chapter7;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class PrimeNumCount {
    public long primeCount(long n){
        return LongStream.rangeClosed(2, n)
                //.parallel()
                .filter(x -> BigInteger.valueOf(x).isProbablePrime(50))
                .count();
    }

    public static void main(String[] args){
        long st = System.nanoTime();
        System.out.println(new PrimeNumCount().primeCount((long) Math.pow(10, 5)));
        long ed = System.nanoTime();
        System.out.println((ed-st)/1000000);
    }
}
