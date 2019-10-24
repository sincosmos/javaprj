package com.sincosmos.algorithms.btree;

public class BTreeNode {
    // m 是 B 树的阶数
    private int m;
    // 节点的数据信息，包含 key (索引列) 和其它字段
    // 每个节点最多包含 m 个元素，这些元素按照 key 的升序排列
    private Object[] elements;
    // 节点的子树指针
    // 每个元素有左子树和右子树，左子树的所有元素都比该元素小，右子树的所有元素都比该元素大
    // rows[i] (0<=i<m) 的左子树是 children[i], 右子树是 children[i+1]
    private BTreeNode[] children;

    private boolean leaf;

    public BTreeNode(int m){
        this.m = m;
        this.elements = new Object[m];
        this.children = new BTreeNode[m+1];
    }

}
