package com.sincosmos.algorithms.leetcode;

import java.util.*;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * Example:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */
public class P155MinStack {
    /** initialize your data structure here. */
    private LinkedList<Integer> stack;
    private TreeMap<Integer, Integer> vCount;
    private int min = Integer.MAX_VALUE;
    public P155MinStack() {
        stack = new LinkedList<>();
        vCount = new TreeMap<>();
    }

    public void push(int x) {
        stack.push(x);
        vCount.put(x, vCount.getOrDefault(x, 0) + 1);
        if(x < min) min = x;
    }

    public void pop() {
        if(stack.size()==0) return;
        int x = stack.pop();
        if(vCount.get(x) == 1) {
            vCount.remove(x);
            if(x == min){
               if(vCount.size() > 0) min = vCount.firstKey();
               else min = Integer.MAX_VALUE;
            }
        }else{
            vCount.put(x, vCount.get(x) - 1);
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return this.min;
    }
}

class MinStack{
    /** initialize your data structure here. */
    private Deque<Integer> stack;
    private int min = Integer.MAX_VALUE;
    public MinStack() {
        stack = new ArrayDeque<>();
    }

    public void push(int x) {
        if(x <= min) {
            //保存前一个最小值
            stack.push(min);
            //更新最小值
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        int x = stack.pop();
        if(x == min){
            //取出前一个最小值，作为最小值
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
