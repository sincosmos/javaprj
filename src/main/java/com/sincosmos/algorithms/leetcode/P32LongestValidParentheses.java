package com.sincosmos.algorithms.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class P32LongestValidParentheses {

    public int longestValidParentheses(String s) {
        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(-1);
        for(int i=0; i<s.length(); ++i){
            if(s.charAt(i) == '('){
                stack.add(i);
            }else{
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
}
