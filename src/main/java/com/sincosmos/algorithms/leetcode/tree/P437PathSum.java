package com.sincosmos.algorithms.leetcode.tree;

public class P437PathSum {
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        if(root.left == null && root.right == null && root.val == sum){
            return 1;
        }
        int withRoot = pathSum(root.left, sum - root.val) + pathSum(root.right, sum - root.val);
        int left = pathSum(root.left, sum);
        int right = pathSum(root.right, sum);
        return withRoot + left + right;
    }
}
