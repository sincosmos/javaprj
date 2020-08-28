package com.sincosmos.algorithms.swordoffer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

public class P30MinStack {
    private Deque<Integer> stack = new LinkedList<>();
    private TreeMap<Integer, Integer> count = new TreeMap<>();
    public P30MinStack() {
    }

    public void push(int x) {
        stack.push(x);
        count.put(x, 1 + count.getOrDefault(x, 0));
    }

    public void pop() {
        int x = stack.pop();
        int cnt = count.get(x);
        if(cnt==1){
            count.remove(x);
        }else{
            count.put(x, cnt-1);
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return count.firstEntry().getKey();
    }
}

class MinStack{
    private Deque<Integer> stack = new LinkedList<>();
    private int min = Integer.MAX_VALUE;
    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        if(x <= min){
            //previous min
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        int x = stack.pop();
        if(x == min){
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min;
    }
}