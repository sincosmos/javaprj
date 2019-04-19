package com.sincosmos.algorithms.leetcode.linkedlist;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example:
 *
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 */
public class P86PartitionList {
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;
        ListNode cur = head;
        ListNode lesserHead = null, geqerHead = null;
        ListNode lesserTail = null, geqerTail = null;

        while(cur != null){
            if(cur.val < x){
                if(lesserHead == null){//first element less than x will be new head
                    lesserHead = cur;
                    lesserTail = cur;
                }else{
                    lesserTail.next = cur;
                    lesserTail = cur;
                }
            }else{//if the element geq x
                if(geqerHead == null){
                    geqerHead = cur;
                    geqerTail = cur;
                }else{
                    geqerTail.next = cur;
                    geqerTail = cur;
                }
            }
            cur = cur.next;
        }

        if(lesserHead != null){//in case all elements are geq x
            lesserTail.next = geqerHead;
            if(geqerTail != null) geqerTail.next = null;
            return lesserHead;
        }else{
            return head;
        }
    }

    public static void main(String[] args){
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(4);
        head.next = second;
        ListNode third = new ListNode(3);
        second.next = third;
        ListNode forth = new ListNode(2);
        third.next = forth;
        ListNode fifth = new ListNode(5);
        forth.next = fifth;
        ListNode sixth = new ListNode(2);
        fifth.next = sixth;

        ListNode p = new P86PartitionList().partition(head, 3);

        while(p!=null){
            System.out.print(p.val + " ");
        }
    }
}
