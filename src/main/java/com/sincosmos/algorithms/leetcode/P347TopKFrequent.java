package com.sincosmos.algorithms.leetcode;

import java.util.*;
import java.util.stream.IntStream;

public class P347TopKFrequent {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        IntStream.of(nums).forEach(x->{
            counts.put(x, counts.getOrDefault(x, 0) + 1);
        });

        //PriorityQueue 优先级最小的元素在堆顶（小顶堆）
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(counts::get));

        counts.keySet().forEach(x -> {
            heap.add(x);
            if(heap.size() > k){
                //heap 中出现次数 第 k+1 大的元素被删除
                heap.poll();
            }
        });

        List<Integer> topK = new LinkedList<>();
        while(!heap.isEmpty()){
            topK.add(heap.poll());
        }
        Collections.reverse(topK);
        return topK;
    }
}
