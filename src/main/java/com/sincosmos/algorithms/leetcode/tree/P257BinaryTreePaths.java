package com.sincosmos.algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P257BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null) return Collections.emptyList();

        List<String> rtn = new ArrayList<>();
        if(root.left == null && root.right == null){
            rtn.add(String.valueOf(root.val));
        }else {

            for (String left : binaryTreePaths(root.left)) {
                rtn.add(root.val + "->" + left);
            }
            for (String right : binaryTreePaths(root.right)) {
                rtn.add(root.val + "->" + right);
            }
        }

        return rtn;
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
        n2.right = n5;

        System.out.println(new P257BinaryTreePaths().binaryTreePaths(n1));

    }
}


