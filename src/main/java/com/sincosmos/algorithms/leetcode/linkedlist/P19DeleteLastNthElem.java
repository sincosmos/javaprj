package com.sincosmos.algorithms.leetcode.linkedlist;

public class P19DeleteLastNthElem {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, slow = head;
        while(n>=0){
            if(fast.next == null){
                return head.next;
            }else{
                fast = fast.next;
            }
            n--;
        }
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return head;
    }
}
