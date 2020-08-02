package com.sincosmos.algorithms;

import java.util.TreeMap;

/**
 * 二叉树，求每层最大节点
 * 单链表，给定区间<start, end>，删除区间中的节点
 */
public class BinaryTree {
    private static class Node{
        private int v;
        private Node left;
        private Node right;
        public Node(int v){
            this.v = v;
        }
    }

    private Node head;

    public void levelMaxElem(BinaryTree bt){
        if(bt == null || bt.head == null) return;
        int level = 0;
        TreeMap<Integer, Integer> levelMax = new TreeMap<>();
        traverse(bt.head, 0, levelMax);
        levelMax.forEach((l, m) -> {
            System.out.println("maximum value of level " + l + "  is: " + m);
        });
    }

    public void traverse(Node h, int level, TreeMap<Integer, Integer> levelMax){
        if(h==null) return;
        if(!levelMax.containsKey(level) || levelMax.get(level) < h.v)
            levelMax.put(level, h.v);
        traverse(h.left, level + 1, levelMax);
        traverse(h.right, level + 1, levelMax);
    }

    public int height(Node node){
        if(node == null) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;

    }
}


