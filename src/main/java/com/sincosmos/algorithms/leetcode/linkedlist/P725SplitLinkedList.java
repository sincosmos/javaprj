package com.sincosmos.algorithms.leetcode.linkedlist;

//@ToDo wrong answer
public class P725SplitLinkedList {
    public ListNode[] splitListToParts(ListNode root, int k) {
        int len = 0;
        ListNode cur = root;
        while(cur != null){
            len++;
            cur = cur.next;
        }

        ListNode[] splits = new ListNode[k];

        if(k>=len){
            cur = root;
            int i = 0;
            while(cur != null){
                splits[i] = cur;
                cur = cur.next;
                splits[i].next = null;
                i++;
            }
        }else{
           int even = len / k;
           int remaining = len % k;
           cur = root;
           int i = 0;
           int curLen = 0;
           int maxLen = even;

           while(cur != null){
               if(maxLen == even && remaining > 0){
                   maxLen = even + 1;
                   remaining--;
               }
               if(curLen < maxLen){
                   if(curLen == 0) {
                       splits[i] = cur;
                       i++;
                       curLen++;
                       cur = cur.next;
                   }else if(curLen == maxLen-1){
                       //split
                       ListNode tmp = cur.next;
                       cur.next = null;
                       cur = tmp;
                       curLen = 0;
                       maxLen = even;
                   }else{
                       curLen++;
                       cur = cur.next;
                   }
               }
           }
        }

        return splits;
    }

    public static void main(String[] args){
        ListNode a = new ListNode(1);

        ListNode b = new ListNode(2);
        a.next = b;
        ListNode c = new ListNode(3);
        b.next = c;
        /*ListNode d = new ListNode(4);
        c.next = d;
        ListNode e = new ListNode(5);
        d.next = e;
        ListNode f = new ListNode(6);
        e.next = f;
        ListNode g = new ListNode(7);
        f.next = g;*/

        new P725SplitLinkedList().splitListToParts(a, 3);
    }
}
