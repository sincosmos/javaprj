package com.sincosmos.algorithms.leetcode.tree;

import java.util.*;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class P107BinaryTreeRLOT {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
        Collections.reverse(levelOrderElems);
        return levelOrderElems;
    }
}
