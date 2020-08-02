package com.sincosmos.algorithms.leetcode;

import java.util.*;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class P102BinaryTreeLOT {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) return Collections.emptyList();
        List<List<Integer>> levelOrderElems = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int curLevelSize = queue.size();
            List<Integer> curLevel = new ArrayList<>();
            while(curLevelSize > 0){
                TreeNode cur = queue.poll();
                curLevel.add(cur.val);
                curLevelSize--;
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
            levelOrderElems.add(curLevel);
        }
        return levelOrderElems;
    }

    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        if(leftDepth == 0){
            return rightDepth + 1;
        }else if(rightDepth == 0){
            return leftDepth + 1;
        }else{
            return Math.min(leftDepth, rightDepth) + 1;
        }
    }

    public int height(TreeNode node){
        if(node == null) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;

    }

    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        if(Math.abs(height(root.left) - height(root.right))>1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }
}
