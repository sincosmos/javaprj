package com.sincosmos.algorithms;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BitMap {
    /**
     * @param arr 没有重复元素，非负整数
     */
    public static void bitMapSort(int[] arr){
        BitSet map = new BitSet();
        for(int i=0; i<arr.length; ++i){
            map.set(arr[i]);
        }
        AtomicInteger idx = new AtomicInteger(0);
        map.stream().forEach(v->arr[idx.getAndIncrement()] = v);
    }

    public static boolean hasDuplicateElement(int[] arr){
        BitSet map = new BitSet();
        for(int i=0; i<arr.length; ++i){
            if(map.get(arr[i])){
                return true;
            }else{
                map.set(arr[i]);
            }
        }
        return false;
    }

    public static int findNotDuplicateElement(int[] arr){
        BitSet map1 = new BitSet();
        BitSet map2 = new BitSet();
        for(int i=0; i<arr.length; ++i){
            if (map1.get(arr[i])) {
                map2.set(arr[i]);
            }else{
                map1.set(arr[i]);
            }
        }
        int idx = -1;
        for(int i=0; i<map1.cardinality(); ++i){
            idx = map1.nextSetBit(idx + 1);
            if(!map2.get(idx)) return idx;
        }
        return -1;
    }
}
