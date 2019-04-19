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
        while(headA != null){
            ListNode cur = headB;
            while(cur!=null){
                if(cur == headA){
                    return cur;
                }else{
                    cur = cur.next;
                }
            }
            headA = headA.next;
        }
        return null;
    }
}
