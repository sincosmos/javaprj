package com.sincosmos.collection;

import java.util.*;

public class PowerSet {

    public static Set<Set<? extends Number>> recursivePowerSetMoreToLess(Set<? extends Number> set){
        Set<Set<? extends Number>> rtn = new HashSet<>();
        rtn.add(set);
        Iterator<? extends Number> iter = set.iterator();
        while(iter.hasNext()){
            // quite inefficient, create 2^n * 2^(n-1) * .... subsets,
            // and ((n-2) * 2^(n-1)) * ((n-3) * 2^(n-2)) of which are duplicate
            // and would be replaced again and again in the result set
            Set<? extends Number> tmp = new HashSet<>(set);
            tmp.remove(iter.next());
            rtn.addAll(recursivePowerSetMoreToLess(tmp));
        }
        return rtn;
    }

    public static Set<Set<? extends Number>> recursivePowerSetLessToMore(Set<? extends Number> set){
        Set<Set<? extends Number>> rtn = new HashSet<>();
        if(set.isEmpty()){
            rtn.add(Collections.emptySet());
            return rtn;
        }

        List<? extends Number> list = new ArrayList<>(set);
        Number head = list.get(0);
        Set<? extends Number> rest = new HashSet<>(list.subList(1, list.size()));
        for(Set<? extends Number> sub : recursivePowerSetLessToMore(rest)){
            Set<Number> tmp = new HashSet<>();
            tmp.add(head);
            tmp.addAll(sub);
            rtn.add(tmp);
            rtn.add(sub);
        }

        return rtn;
    }

    /**
     * 核心算法思想
     * 1）空集是任何集合的子集，首先设 powerset = {{}}
     * 2）遍历目标集合
     *    对于每一个元素，将其依次加入到 powerset 的每个元素（即子集）中，得到新的集合序列，加入 powerset
     *    直到遍历到最后一个元素
     * 示例：
     *    set = {1,2,3}
     *    powerset = {{}}
     *    powerset = {{}} + {{1}} = {{}, {1}}
     *    powerset = {{}, {1}} + {{2}, {1,2}} = {{}, {1}, {2}, {1,2}}
     *    powerset = {{}, {1},{2}, {1,2}} + {{3},{1,3}, {2,3}, {1,2,3}} = {{}, {1},{2}, {1,2},{3},{1,3}, {2,3}, {1,2,3}}
     * @param set
     * @param <T>
     * @return
     */
    public static <T> Set<Set<T>> powerSet(Set<T> set){
        Set<Set<T>> rtn = new HashSet<>();
        rtn.add(Collections.emptySet());
        Iterator<T> iter = set.iterator();
        while(iter.hasNext()){
            T cur = iter.next();
            Set<Set<T>> newSetsWithCurElem = new HashSet<>();

            for (Set<T> numbers : rtn) {
                Set<T> tmp = new HashSet<>(numbers);
                tmp.add(cur);
                newSetsWithCurElem.add(tmp);
            }
            rtn.addAll(newSetsWithCurElem);
        }
        return rtn;
    }

    public static void main(String[] args){
        /*Set<Integer> set = new HashSet<>(Arrays.asList(9,6,7,8,11,134,13,14,15,16));//,18,19,20,21,22,23,24,36,38,39
        long st, ed;
        *//*st = System.currentTimeMillis();
        Set<Set<? extends Number>> powerSet1 = PowerSet.recursivePowerSetMoreToLess(set);
        ed = System.currentTimeMillis();
        System.out.println("recursivePowerSetMoreToLess(ms):: " + (ed-st));
        System.out.println((powerSet1.size()));*//*
        //powerSet1.forEach(System.out::println);

        st = System.currentTimeMillis();
        Set<Set<? extends Number>> powerSet2 = PowerSet.recursivePowerSetLessToMore(set);
        ed = System.currentTimeMillis();
        System.out.println("recursivePowerSetLessToMore(ms):: " + (ed-st));
        System.out.println((powerSet2.size()));
        //powerSet2.forEach(System.out::println);

        st = System.currentTimeMillis();
        Set<Set<Integer>> powerSet3 = PowerSet.powerSet(set);
        ed = System.currentTimeMillis();
        System.out.println("powerSet(ms):: " + (ed-st));
        System.out.println((powerSet3.size()));
        //powerSet3.forEach(System.out::println);*/

        int[] nums = {1,2,3};
        PowerSetAgain p = new PowerSetAgain();
        //p.powerSet(nums).forEach(System.out::println);

        System.out.println(p.powerSet(nums));
    }
}
