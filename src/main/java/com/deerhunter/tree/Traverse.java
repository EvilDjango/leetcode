package com.deerhunter.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Supplier;

/**
 * 四种方式遍历树
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-20
 */
public class Traverse {
    /**
     * 按层遍历
     *
     * @param root
     */
    public static void traverseByLayer(TreeNode root) {
        if (null == root) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node != null) {
                node.visit();
                queue.add(node.getLeftChild());
                queue.add(node.getRightChild());
            }
        }
    }

    /**
     * 前序遍历：递归
     *
     * @param root
     */
    public static void preOrderRecursively(TreeNode root) {
        if (null == root) {
            return;
        }
        root.visit();
        preOrderRecursively(root.getLeftChild());
        preOrderRecursively(root.getRightChild());
    }

    /**
     * 前序遍历：循环
     *
     * @param root
     */
    public static void preOrderByLoop(TreeNode root) {
        if (null == root) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                cur.visit();
                stack.add(cur);
                cur = cur.getLeftChild();
            } else {
                cur = stack.pop().getRightChild();
            }
        }
    }

    /**
     * 中序遍历：递归
     *
     * @param root
     */
    public static void inOrderRecursively(TreeNode root) {
        if (null == root) {
            return;
        }
        inOrderRecursively(root.getLeftChild());
        root.visit();
        inOrderRecursively(root.getRightChild());
    }

    /**
     * 中序遍历：循环
     *
     * @param root
     */
    public static void inOrderByLoop(TreeNode root) {
        if (null == root) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.add(cur);
                cur = cur.getLeftChild();
            } else {
                TreeNode node = stack.pop();
                node.visit();
                cur = node.getRightChild();
            }
        }
    }

    /**
     * 后序遍历：递归
     *
     * @param root
     */
    public static void postOrderRecursively(TreeNode root) {
        if (null == root) {
            return;
        }
        postOrderRecursively(root.getLeftChild());
        postOrderRecursively(root.getRightChild());
        root.visit();
    }

    public static void postOrderByLoop(TreeNode root) {
        if (null == root) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode lastVisit = null;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.add(cur);
                cur = cur.getLeftChild();
            } else {
                TreeNode node = stack.peek();
                if (!node.hasRightChild() || node.getRightChild() == lastVisit) {
                    node.visit();
                    lastVisit = node;
                    stack.pop();
                    cur = null;
                } else {
                    cur = node.getRightChild();
                }
            }
        }
    }

}