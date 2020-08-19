package com.sincosmos.algorithms.array;

import java.util.*;

public class ArrayHandling2 {
    private TreeMap<Integer, Integer> frequency;

    public ArrayHandling2(){
        frequency = new TreeMap<>(Collections.reverseOrder());
    }

    public void calFrequency(int[] arr){
        for(int i=0; i<arr.length; ++i){
            frequency.put(arr[i], frequency.getOrDefault(arr[i], 0) + 1);
        }
    }

    public int getKthLargest(int k){
        if(frequency.size() < k){
            throw new IndexOutOfBoundsException();
        }
        return frequency.keySet().toArray(new Integer[0])[ k-1 ];
    }

    public double getMedian(int split){
        SortedMap<Integer, Integer> remaining = frequency.tailMap(split);
        int total = remaining.values().parallelStream().reduce(0, Integer::sum);
        double median = 0f;
        if((total & 1) == 0){
            //剩余偶数个
            int cur=0, key1=0, key2=0;
            int index1 = total >> 1;
            int index2 = index1 + 1;

            Iterator<Map.Entry<Integer,Integer>> iter = remaining.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry<Integer,Integer> entry = iter.next();
                cur += entry.getValue();
                if(cur>=index1){
                    key1 = entry.getKey();
                    if(cur>=index2){
                        key2=key1;
                    }else{
                        key2=iter.next().getKey();
                    }
                    break;
                }

            }
            median = key1/2.0 + key2/2.0;
        }else{
            //剩余奇数个
            int cur = 0;
            int index = (total >> 1) + 1;
            for(Map.Entry<Integer, Integer> entry : remaining.entrySet()){
                cur += entry.getValue();
                if(cur>=index){
                    median = entry.getKey();
                    break;
                }
            }
        }
        return median;
    }

    public static void main(String[] args) {
        //int[] arr = new int[]{1, 1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8,
         //       8, 9, 9, 10, 10, 11, 11, 12, 12, 11, 12, 7, 8, 9, 11, 11, 12, 2, 11, 4, 4};

        int[] arr = new int[] {1,1,2,3,4,5,6,7,8,9,10,11,11,12,13,13,13};

        ArrayHandling2 ah = new ArrayHandling2();
        ah.calFrequency(arr);
        int kth = ah.getKthLargest(11);
        System.out.println("The eleventh largest number is: " + kth);
        double median = ah.getMedian(kth);
        System.out.println("剩余数字中的中位数: " + median);
    }
}
