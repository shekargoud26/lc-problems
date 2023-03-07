package com.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

public class BinaryTreePreOrderTraversal {

    /**
     *
     * Given the root of a binary tree, return the preorder traversal of its nodes' values.
     * 0191-618B
     *
     */

    //   Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {

    }

    public void preOrder(List<Integer> preOrder, TreeNode root){
        if(root == null)
            return;
        preOrder.add(root.val);
        preOrder(preOrder, root.left);
        preOrder(preOrder, root.right);
    }
}
