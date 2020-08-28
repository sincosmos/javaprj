package com.sincosmos.algorithms.swordoffer;

import java.util.ArrayDeque;
import java.util.Deque;

public class P06ReverseList {
    public int[] reversePrint(ListNode head) {
        ListNode cur = head;
        Deque<Integer> stack = new ArrayDeque<>();
        while(cur!=null){
            stack.push(cur.val);
            cur = cur.next;
        }
        int[] reverse = new int[stack.size()];
        int i = 0;
        while(!stack.isEmpty()){
            reverse[i] = stack.pop();
            i++;
        }
        return reverse;
    }
}
