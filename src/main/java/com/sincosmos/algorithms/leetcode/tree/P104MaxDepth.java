package com.sincosmos.algorithms.leetcode.tree;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P104MaxDepth {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * Breadth first traverse
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if(root == null) return 0;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        int max = 0;
        while(!deque.isEmpty()){
            max += 1;
            int size = deque.size();
            for(int i=0; i<size; ++i){
                TreeNode cur = deque.pollFirst();
                if(cur.left != null) deque.add(cur.left);
                if(cur.right != null) deque.add((cur.right));
            }
        }
        return max;
    }


    /**
     * Depth first
     * @param root
     * @return
     */
    public int maxDepth3(TreeNode root) {
        if(root == null) return 0;
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        int max = 0;
        stack.push(new Pair<>(root, 1));
        while(!stack.isEmpty()){
            Pair<TreeNode, Integer> top = stack.pop();
            int curDepth = top.getValue();

            max = Math.max(max, curDepth);

            if(top.getKey().right != null)  stack.push(new Pair<>(top.getKey().right, curDepth + 1));
            if(top.getKey().left != null) stack.push(new Pair<>(top.getKey().left, curDepth + 1));
        }
        return max;
    }

    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return Math.abs(maxDepth(root.right) - maxDepth(root.left))<=1
                && isBalanced(root.right) && isBalanced(root.left);
    }

    public static void main(String[] args){
        TreeNode n1 = new TreeNode(1);

        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;

        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n2.left = n4;
        n3.right = n5;

        System.out.println(new P104MaxDepth().maxDepth2(n1));

    }


}
