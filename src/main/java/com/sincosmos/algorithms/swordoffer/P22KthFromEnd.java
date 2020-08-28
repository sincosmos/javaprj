package com.sincosmos.algorithms.swordoffer;

public class P22KthFromEnd {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode cur1 = head, cur2=head;
        int i=0;
        while(cur1 != null && i<k){
            ++i;
            cur1 = cur1.next;
        }

        //不够 k 个元素
        if(i<k) return null;

        while(cur1 != null){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur2;
    }
}
