package com.sincosmos.algorithms.leetcode.tree;

public class P543RoundLength {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;

        int throughRoot = maxDepth(root.left) + maxDepth(root.right);
        int left = diameterOfBinaryTree(root.left);
        int right = diameterOfBinaryTree(root.right);

        return Math.max(throughRoot, Math.max(left, right));
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);

        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;

        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n2.left = n4;
        n2.right = n5;

        System.out.println(new P543RoundLength().diameterOfBinaryTree(n1));
    }
}
