package com.sincosmos.algorithms.leetcode.linkedlist;

public class P24SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next == null) return head;

        ListNode first = head, second = head.next;
        ListNode newHead = second, tail = null;

        while(first != null && second != null){
            //swap
            first.next = second.next;
            second.next = first;
            if(tail != null) tail.next = second;

            //next tail
            tail = first;

            first = tail.next;
            if(tail.next != null) {
                second = tail.next.next;
            }else {
                second = null;
            }
        }

        return newHead;
    }
}
