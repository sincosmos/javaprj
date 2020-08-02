package com.sincosmos.algorithms.leetcode.linkedlist;

public class P328OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode odd = head, even = head.next, oddHead = head, evenHead = head.next;
        while(odd != null && even != null){
            odd.next = even.next;
            if(even.next != null){
                odd = even.next;
                even.next = odd.next;
                even = odd.next;
            }else{
                break;
            }
        }
        odd.next = evenHead;
        return oddHead;
    }
}
