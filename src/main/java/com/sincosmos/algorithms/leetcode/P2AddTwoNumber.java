package com.sincosmos.algorithms.leetcode;

import com.sincosmos.algorithms.leetcode.linkedlist.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class P2AddTwoNumber {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0, cur;
        ListNode head = null, tail = null;
        while(l1 != null && l2!=null){
            cur = l1.val + l2.val + carry;
            carry = cur / 10;
            if(cur>=10) cur-=10;
            ListNode node = new ListNode(cur);
            if(tail == null) head=tail=node;
            else {
                tail.next = node;
                tail = node;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null){
           cur = l1.val + carry;
           carry = cur / 10;
            if(cur>=10) cur-=10;
            ListNode node = new ListNode(cur);
            if(tail == null) head=tail=node;
            else {
                tail.next = node;
                tail = node;
            }
            l1 = l1.next;
        }
        while(l2 != null){
            cur = l2.val + carry;
            carry = cur / 10;
            if(cur>=10) cur-=10;
            ListNode node = new ListNode(cur);
            if(tail == null) head=tail=node;
            else {
                tail.next = node;
                tail = node;
            }
            l2 = l2.next;
        }
        if(carry != 0){
            ListNode node = new ListNode(carry);
            tail.next = node;
        }
        return head;
    }
}
