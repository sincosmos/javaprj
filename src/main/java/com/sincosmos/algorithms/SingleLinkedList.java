package com.sincosmos.algorithms;

public class SingleLinkedList {
    private static class Node{
        private int v;
        private Node next;
        public Node(int v){
            this.v = v;
        }
    }

    Node first;

    public void remove(Node st, Node ed){
        Node cur = first;
        while(cur != null && cur.next != st){
            cur = cur.next;
        }
        if(cur != null) cur.next = ed.next;
    }
}
