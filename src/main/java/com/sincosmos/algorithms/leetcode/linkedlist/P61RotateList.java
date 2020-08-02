package com.sincosmos.algorithms.leetcode.linkedlist;

import com.sincosmos.algorithms.leetcode.linkedlist.ListNode;

/**
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * Example 2:
 *
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 */
public class P61RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return head;
        int len = 1;
        ListNode cur = head;
        while(cur.next != null){
            len++;
            cur = cur.next;
        }
        //Now cur point to the last element
        k = k % len;
        if(k != 0){
            ListNode newTail = head;
            for(int i=1; i<len-k; ++i){
                newTail = newTail.next;
            }

            ListNode newHead = newTail.next;
            newTail.next = null;
            cur.next = head;
            return newHead;
        }else{
            return head;
        }
    }
}

