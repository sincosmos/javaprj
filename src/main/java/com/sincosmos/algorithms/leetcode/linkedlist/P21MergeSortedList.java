package com.sincosmos.algorithms.leetcode.linkedlist;


/*
Merge two sorted linked lists and return it as a new list.
The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
*/
public class P21MergeSortedList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        ListNode rtn, curRtn, cursor1, cursor2;

        if(l1.val < l2.val){
            rtn = curRtn = l1;
            cursor1 = l1.next;
            cursor2 = l2;
        }else{
            rtn = curRtn = l2;
            cursor1 = l1;
            cursor2 = l2.next;
        }

        while(cursor1 != null && cursor2 != null){
            if(cursor1.val < cursor2.val){
                curRtn.next = cursor1;
                curRtn = cursor1;
                cursor1 = cursor1.next;
            }else{
                curRtn.next = cursor2;
                curRtn = cursor2;
                cursor2 = cursor2.next;
            }
        }
        if(cursor1 != null) curRtn.next = cursor1;
        if(cursor2 != null) curRtn.next = cursor2;

        return rtn;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        if(l1.val < l2.val){
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        }else{
            l1.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }
}
