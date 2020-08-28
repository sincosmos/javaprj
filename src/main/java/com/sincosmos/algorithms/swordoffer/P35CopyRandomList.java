package com.sincosmos.algorithms.swordoffer;


public class P35CopyRandomList {
    public Node copyRandomList(Node head) {
        Node cur = head;
        while(cur != null) {
            Node tmp = cloneNode(cur);
            cur.next = tmp;
            cur = tmp.next;
        }

        cur = head;

        while(cur != null){
            //if cur!=null, then cur.next != null
            if(cur.random != null) cur.next.random = cur.random.next;
            cur = cur.next.next;
        }

        cur = head;
        Node newHead = null, newCur = null;
        while(cur != null){
            //偶数节点是原节点，next 奇数节点是新节点
            newCur = cur.next;
            if(newHead==null) newHead = newCur;
            cur.next = newCur.next;
            if(cur.next != null) newCur.next = cur.next.next;
            cur = cur.next;
        }
        return newHead;
    }

    Node cloneNode(Node node){
        Node tmp = new Node(node.val);
        tmp.next = node.next;
        return tmp;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}