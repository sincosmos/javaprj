package com.sincosmos.algorithms.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class P234PalindromeList {
    public boolean isPalindrome(ListNode head) {
        List<Integer> sb = new ArrayList<>();
        ListNode cur = head;
        while(cur != null){
            sb.add(cur.val);
            cur = cur.next;
        }
        for(int i=0, j=sb.size()-1;i<j;i++, j--){
            if(!(sb.get(i).equals(sb.get(j))))
                return false;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        ListNode fast = head, mid = head;

        while(fast != null){
            if(fast.next != null){
                fast = fast.next.next;
            }else{
                fast = null;
            }

            mid = mid.next;
        }

        ListNode reversePartHead = null, leftPartHead = mid;

        while(mid != null){
            leftPartHead = mid.next;
            mid.next = reversePartHead;
            reversePartHead = mid;
            mid = leftPartHead;
        }

        ListNode cur = head;
        while(reversePartHead != null){
            if(cur.val != reversePartHead.val){
                return false;
            }
            reversePartHead = reversePartHead.next;
            cur = cur.next;
        }

        return true;
    }


    public static void main(String[] args){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        a.next = b;
        ListNode c = new ListNode(2);
        b.next = c;
        ListNode d = new ListNode(1);
        c.next = d;
        System.out.println(new P234PalindromeList().isPalindrome2(a));
    }
}
