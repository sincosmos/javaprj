package com.sincosmos.algorithms.leetcode;

import java.util.*;

public class P692TopKFrequentWords {
    public List<String> topKFrequent1(String[] words, int k) {
        TreeSet<String>[] frequency = new TreeSet[words.length + 1];
        Map<String, Integer> counts = new HashMap<>();
        Arrays.asList(words).forEach(x -> counts.put(x, counts.getOrDefault(x, 0) + 1));

        for(String word : counts.keySet()){
            if(frequency[counts.get(word)] == null){
                frequency[counts.get(word)] = new TreeSet<>();
            }
            frequency[counts.get(word)].add(word);
        }

        List<String> rtn = new ArrayList<>();
        for(int i=frequency.length - 1; i>=0 && rtn.size()<k; i--){
            if(frequency[i] == null) continue;
            while(!frequency[i].isEmpty() && rtn.size()<k ){
                rtn.add(frequency[i].pollFirst());
            }
        }

        return rtn;
    }

    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> counts = new HashMap<>();
        Arrays.asList(words).forEach(x -> counts.put(x, counts.getOrDefault(x, 0) + 1));

        //数据量较大时不可取
        List<String> rtn = new ArrayList<>(counts.keySet());

        //排序，次数较多的在前，次数相同则字母较小的在前
        Collections.sort(rtn, (w1, w2)-> {
            if(counts.get(w1) == counts.get(w2)){
                return w1.compareTo(w2);
            }
            return counts.get(w2).compareTo(counts.get(w1));
        });

        return rtn.subList(0, k);
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counts = new HashMap<>();
        Arrays.asList(words).forEach(x -> counts.put(x, counts.getOrDefault(x, 0) + 1));

        //小顶堆，（优先级）较小的元素在前面
        PriorityQueue<String> heap = new PriorityQueue<>((w1, w2) -> {
            if(counts.get(w1) == counts.get(w2)){
                return w2.compareTo(w1);
            }
            return counts.get(w1).compareTo(counts.get(w2));
        });

        for(String word : counts.keySet()){
            heap.add(word);
            if(heap.size()>k){
                heap.poll();
            }
        }

        List<String> rtn = new LinkedList<>();
        while(!heap.isEmpty())
            rtn.add(heap.poll());

        Collections.reverse(rtn);
        return rtn;
    }

    public List<String> topKFrequentCorrect(String[] words, int k) {
        Map<String, Integer> counts = new HashMap<>();
        //小顶堆，（优先级）较小的元素在前面
        PriorityQueue<String> heap = new PriorityQueue<>(Comparator.comparing(counts::get).reversed());
        Arrays.asList(words).forEach(x -> {
            counts.put(x, counts.getOrDefault(x, 0) + 1);
            if(heap.size() < k) heap.add(x);
            else{
                if(counts.get(heap.peek()) < counts.get(x)){
                    heap.poll();
                    heap.add(x);
                }
            }
        });


        return new ArrayList(heap);
    }
}
