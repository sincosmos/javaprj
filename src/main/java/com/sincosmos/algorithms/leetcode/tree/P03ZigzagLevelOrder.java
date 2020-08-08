package com.sincosmos.algorithms.leetcode.tree;

import java.util.*;

public class P03ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root==null) return Collections.emptyList();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        int level = 0;
        List<List<Integer>> levelOrder = new ArrayList<>();
        while(!deque.isEmpty()){
            int levelSize = deque.size();
            List<Integer> levelVal = new ArrayList<>();
            while(levelSize > 0){
                TreeNode cur = deque.pollFirst();
                if(cur.left != null) deque.add(cur.left);
                if(cur.right != null) deque.add(cur.right);
                levelVal.add(cur.val);
                levelSize--;
            }
            if((level & 1) == 1){
                //奇数层
                Collections.reverse(levelVal);
            }
            levelOrder.add(levelVal);
            level++;
        }

        return levelOrder;
    }
}
