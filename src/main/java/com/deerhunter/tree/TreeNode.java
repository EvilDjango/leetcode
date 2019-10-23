package com.deerhunter.tree;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.io.IOException;
import java.util.ArrayDeque;
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
    private int data;
    private TreeNode ancestor;
    private TreeNode leftChild;
    private TreeNode rightChild;

    public TreeNode(int data) {
        this.data = data;
    }

    public TreeNode(int data, TreeNode ancestor) {
        this.data = data;
        this.ancestor = ancestor;
    }

    public TreeNode(int data, TreeNode ancestor, TreeNode leftChild, TreeNode rightChild) {
        this.data = data;
        this.ancestor = ancestor;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public TreeNode getAncestor() {
        return ancestor;
    }

    public void setAncestor(TreeNode ancestor) {
        this.ancestor = ancestor;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void visit() {
        System.out.println(data);
    }

    public boolean hasLeftChild() {
        return leftChild != null;
    }

    public boolean hasRightChild() {
        return rightChild != null;
    }

    public static TreeNode createTree(int[] nums) {
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
            node.setLeftChild(leftChild);
            queue.add(leftChild);
            if (i < len) {
                TreeNode rightChild = new TreeNode(nums[i++], node);
                node.setRightChild(rightChild);
                queue.add(rightChild);
            }
        }
        return root;
    }

}