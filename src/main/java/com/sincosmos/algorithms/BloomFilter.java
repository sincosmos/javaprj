package com.sincosmos.algorithms;

import java.math.BigInteger;

public class BloomFilter {
    private BigInteger bitmap;
    private int NUM_SLOTS=1024;

    public BloomFilter(){
        bitmap = new BigInteger("0");
    }

    public void printBitMap(){
        System.out.println(bitmap.bitCount());
        System.out.println(bitmap.toByteArray());
        System.out.println(bitmap.toString(2));
    }

    public void addElement(String elem){
        BigInteger hashCode1 = new BigInteger("1").shiftLeft(nativeHashCode(elem));
        BigInteger hashCode2 = new BigInteger("1").shiftLeft(rotatingHash(elem));
        synchronized (this){
            bitmap = bitmap.or(hashCode1).or(hashCode2);
        }
    }

    public boolean check(String msg){
        return bitmap.testBit(nativeHashCode(msg)) && bitmap.testBit(rotatingHash(msg));
    }

    private int nativeHashCode(String elem){
        return Math.abs(elem.hashCode()) % NUM_SLOTS;
    }

    private int rotatingHash(String elem){
        int hash, i;
        for (hash = elem.length(), i = 0; i < elem.length(); ++i)
            hash = (hash << 4) ^ (hash >> 28) ^ elem.charAt(i);
        return Math.abs((hash % 33)) % NUM_SLOTS;
    }

    public static void main(String[] args){
        BloomFilter bloomFilter = new BloomFilter();
        bloomFilter.addElement("test");
        bloomFilter.addElement("BloomFilter");
        bloomFilter.addElement("LOL point");

        System.out.println("msg::" + "test ->" + bloomFilter.check("test") );
        System.out.println("msg::" + "BloomFilter ->" + bloomFilter.check("BloomFilter") );
        System.out.println("msg::" + "LOL point ->" + bloomFilter.check("LOL point") );
        System.out.println("msg::" + "abc ->" + bloomFilter.check("abc") );
        System.out.println("msg::" + "cba ->" + bloomFilter.check("cba") );
        System.out.println("msg::" + "LOL point ->" + bloomFilter.check("LOL point") );

        bloomFilter.printBitMap();
    }

}
