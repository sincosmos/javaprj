package com.sincosmos.algorithms.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class P150EvalRPN {
    public int evalRPN(String[] tokens) {
        Deque<String> stack = new ArrayDeque<>();
        int first, second;
        for(String token : tokens){
            switch (token){
                case "+":
                    second = Integer.valueOf(stack.pop());
                    first = Integer.valueOf(stack.pop());
                    stack.push(String.valueOf(first+second));
                    break;
                case "-":
                    second = Integer.valueOf(stack.pop());
                    first = Integer.valueOf(stack.pop());
                    stack.push(String.valueOf(first-second));
                    break;
                case "*":
                    second = Integer.valueOf(stack.pop());
                    first = Integer.valueOf(stack.pop());
                    stack.push(String.valueOf(first*second));
                    break;
                case "/":
                    second = Integer.valueOf(stack.pop());
                    first = Integer.valueOf(stack.pop());
                    stack.push(String.valueOf(first/second));
                    break;
                default:
                    stack.push(token);
            }
        }
        return Integer.valueOf(stack.pop());
    }
}
