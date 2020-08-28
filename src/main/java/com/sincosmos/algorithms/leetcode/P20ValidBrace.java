package com.sincosmos.algorithms.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class P20ValidBrace {
    public boolean isValid(String s) {
        if(s==null) return false;
        if("".equals(s)) return true;

        Set<String> st = new HashSet<>();
        st.add("(");
        st.add("[");
        st.add("{");

        Deque<String> stack = new ArrayDeque<>();
        for(int i=0; i<s.length(); ++i){
            String cur = s.substring(i, i+1);
            if(st.contains(cur)){
                stack.push(cur);
            }else{
                if(isPair(stack.peek(),cur)){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isPair(String top, String cur) {
        if("(".equals(top)){
            return ")".equals(cur);
        }else if("[".equals(top)){
            return "]".equals(cur);
        }else if("{".equals(top)){
            return "}".equals(cur);
        }
        return false;
    }
}
