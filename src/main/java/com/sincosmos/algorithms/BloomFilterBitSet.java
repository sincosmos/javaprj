package com.sincosmos.algorithms;

import java.util.BitSet;

public class BloomFilterBitSet {
    private int NUM_SLOTS=15;
    private BitSet bitset1;
    private BitSet bitset2;

    public BloomFilterBitSet(){
        bitset1 = new BitSet(NUM_SLOTS);
        bitset2 = new BitSet(NUM_SLOTS);
    }

    public void printBitMap(){
        System.out.println(bitset1.toByteArray());
        System.out.println(bitset2.toByteArray());
    }

    public void addElement(String elem){
        int hashCode1 = nativeHashCode(elem);
        int hashCode2 = rotatingHash(elem);
        synchronized (this){
            bitset1.set(hashCode1);
            bitset2.set(hashCode2);
        }
    }

    public boolean check(String msg){
        return bitset1.get(nativeHashCode(msg)) && bitset2.get(rotatingHash(msg));
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
        BloomFilterBitSet bloomFilter = new BloomFilterBitSet();
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
