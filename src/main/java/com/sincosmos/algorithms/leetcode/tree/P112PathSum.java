package com.sincosmos.algorithms.leetcode.tree;

public class P112PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        int remaining = sum - root.val;
        if(root.left == null && root.right == null){
            return remaining == 0;
        }
        return hasPathSum(root.left, remaining) || hasPathSum(root.right, remaining);
    }
}
