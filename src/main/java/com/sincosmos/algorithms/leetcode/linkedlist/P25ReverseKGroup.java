package com.sincosmos.algorithms.leetcode.linkedlist;

public class P25ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {

        int len = 0;
        ListNode cur = head;
        while(cur != null){
            len++;
            cur = cur.next;
        }
        if(len < k) return head;

        int canReversed = len / k;
        ListNode newHead = null, curPre = null;

        cur = head;
        for(int reversed = 0; reversed<canReversed; ++reversed){
            ListNode[] ht = reverseGroup(curPre, cur, k);
            if(reversed == 0) newHead = ht[0];
            cur = ht[1].next;
            curPre = ht[1];

        }
        return newHead;
    }

    private ListNode[] reverseGroup(ListNode curPre, ListNode cur, int k) {
        ListNode newHead = null, newTail = null, remainingPartHead;

        for(int i=0; i<k; ++i){
            remainingPartHead = cur.next;
            cur.next = newHead;

            newHead = cur;
            if(i==0) newTail = newHead;
            cur = remainingPartHead;
        }
        newTail.next = cur;
        if(curPre != null) curPre.next = newHead;

        ListNode[] ht = new ListNode[2];
        ht[0] = newHead;
        ht[1] = newTail;
        return ht;
    }

    public static void main(String[] args){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        a.next = b;
        ListNode c = new ListNode(3);
        b.next = c;
        ListNode d = new ListNode(4);
        c.next = d;
        ListNode e = new ListNode(5);
        d.next = e;

        ListNode h = new P25ReverseKGroup().reverseKGroup(a, 2);
        while(h != null){
            System.out.print(h.val + "->");
        }
    }
}
