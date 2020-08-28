package com.sincosmos.algorithms.swordoffer;

import com.sincosmos.algorithms.leetcode.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class P07BuildTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        Map<Integer, Integer> inIndexMap = new HashMap<>();
        for(int i=0; i<inorder.length; ++i){
            inIndexMap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inIndexMap);
    }

    private TreeNode buildTree(int[] preorder, int preSt, int preEd, int[] inorder, int inSt, int inEd,
                               Map<Integer, Integer> inIndexMap){
        if(preSt > preEd){
            return null;
        }
        int rootVal = preorder[preSt];
        TreeNode root = new TreeNode(rootVal);
        if(preSt == preEd) return root;


        int rootInIndex = inIndexMap.get(rootVal);
        int leftSubTreeNum = rootInIndex - inSt;
        int rightSubTreeNum = inEd - rootInIndex;

        root.left = buildTree(preorder, preSt + 1, preSt + leftSubTreeNum
                ,inorder, inSt, rootInIndex - 1, inIndexMap);
        root.right = buildTree(preorder, preSt + leftSubTreeNum + 1, preEd
                ,inorder, rootInIndex + 1, inEd, inIndexMap);
        return root;
    }
}
