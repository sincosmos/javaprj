package com.sincosmos.algorithms.swordoffer;

public class P18DeleteNode {
    public ListNode deleteNode(ListNode head, int val) {
        if(head == null) return null;
        if(head.val == val) return head.next;
        ListNode pre = head;
        ListNode cur = head.next;

        while(cur != null){
            if(cur.val == val){
               pre.next = cur.next;
               break;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }
        return head;

    }
}
