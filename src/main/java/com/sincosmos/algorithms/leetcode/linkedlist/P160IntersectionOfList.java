package com.sincosmos.algorithms.leetcode.linkedlist;

import com.sincosmos.algorithms.leetcode.linkedlist.ListNode;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * For example, the following two linked lists:
 *
 *
 * begin to intersect at node c1.
 */
public class P160IntersectionOfList {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode pa = headA, pb = headB;
        while(pa != null && pb  != null){
            if(pa == pb) return pa;
            else{
                pa = pa.next;
                pb = pb.next;
            }
        }
        if(pa == null && pb!=null) {
            pa = headB;
            while(pa != null){
                if(pb == null) pb = headA;
                if(pa == pb) return pa;
                else{
                    pa = pa.next;
                    pb = pb.next;
                }
            }
        }else if(pb==null && pa!=null) {
            pb = headA;
            while(pb != null){
                if(pa==null) pa=headB;
                if(pa == pb) return pa;
                else{
                    pa = pa.next;
                    pb = pb.next;
                }
            }
        }

        return null;
    }
}
