package com.sincosmos.algorithms.btree;

public class BPlusTreeNode {
    // m 是 B+ 树的阶数
    private int m;
    //
    private String[] keys;
    private BPlusTreeNode[] children;

    private BPlusTreeNode next;

    public BPlusTreeNode(int m){
        this.m = m;
        this.keys = new String[m];
        this.children = new BPlusTreeNode[m+1];
    }
}
