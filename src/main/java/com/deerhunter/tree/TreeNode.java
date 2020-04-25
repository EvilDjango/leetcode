package com.deerhunter.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 树节点
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-20
 */
public class TreeNode {
    public int val;
    public TreeNode ancestor;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode ancestor) {
        this.val = val;
        this.ancestor = ancestor;
    }

    public TreeNode(int val, TreeNode ancestor, TreeNode left, TreeNode right) {
        this.val = val;
        this.ancestor = ancestor;
        this.left = left;
        this.right = right;
    }

    public TreeNode getAncestor() {
        return ancestor;
    }

    public void setAncestor(TreeNode ancestor) {
        this.ancestor = ancestor;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void visit() {
        System.out.println(val);
    }

    public boolean hasLeftChild() {
        return left != null;
    }

    public boolean hasRightChild() {
        return right != null;
    }

    /**
     * 构造一个完全二叉树
     *
     * @param nums
     * @return
     */
    public static TreeNode createCompleteTree(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            throw new IllegalArgumentException();
        }
        Queue<TreeNode> queue = new ArrayDeque<>(len);
        TreeNode root = new TreeNode(nums[0]);
        queue.add(root);
        int i = 1;
        while (i < len) {
            TreeNode node = queue.remove();
            TreeNode leftChild = new TreeNode(nums[i++], node);
            node.setLeft(leftChild);
            queue.add(leftChild);
            if (i < len) {
                TreeNode rightChild = new TreeNode(nums[i++], node);
                node.setRight(rightChild);
                queue.add(rightChild);
            }
        }
        return root;
    }

    /**
     * 构造一个二叉树
     *
     * @param nums
     * @return
     */
    public static TreeNode createTree(Integer[] nums) {
        int len = nums.length;
        if (len == 0 || nums[0] == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(nums[0]);
        queue.add(root);
        int i = 1;
        while (i < len) {
            TreeNode node = queue.remove();
            // 空节点没有子节点
            if (node == null) {
                i += 2;
                continue;
            }

            Integer left = nums[i++];
            TreeNode leftChild = left == null ? null : new TreeNode(left, node);
            node.setLeft(leftChild);
            queue.add(leftChild);

            if (i < len) {
                Integer right = nums[i++];
                TreeNode rightChild = right == null ? null : new TreeNode(right, node);
                node.setRight(rightChild);
                queue.add(rightChild);
            }
        }
        return root;
    }

}
