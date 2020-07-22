package com.sincosmos.algorithms.leetcode.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

public class P445AddTwoList {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur = null;

        Deque<Integer> s1 = new ArrayDeque<>();
        cur = l1;
        while(cur!=null){
            s1.push(cur.val);
            cur = cur.next;
        }

        Deque<Integer> s2 = new ArrayDeque<>();
        cur = l2;
        while(cur!=null){
            s2.push(cur.val);
            cur = cur.next;
        }

        ListNode head = null;
        int sum = 0;
        int carry = 0;
        while(!s1.isEmpty() || !s2.isEmpty() || carry != 0){
            sum = carry;
            sum += s1.isEmpty() ? 0 : s1.pop();
            sum += s2.isEmpty() ? 0 : s2.pop();

            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
            carry = sum / 10;
        }

        return head;
    }
}
