package com.sincosmos.algorithms.leetcode.linkedlist;

import com.sincosmos.algorithms.leetcode.linkedlist.ListNode;

/**
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class P206ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next == null) return head;
        ListNode cur = head, newHead = null, tmpHead;
        while(cur != null){
            tmpHead = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = tmpHead;
        }
        return newHead;
    }

    public ListNode reverseList2(ListNode head) {
        if(head==null || head.next == null) return head;
        //new Head will not be null because head.next is not null
        ListNode newHead = reverseList2(head.next);
        ListNode cur = newHead;
        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = head;
        head.next = null;
        return newHead;
    }
}
