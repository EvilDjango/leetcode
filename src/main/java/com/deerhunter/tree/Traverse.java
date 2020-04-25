package com.deerhunter.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
                queue.add(node.getLeft());
                queue.add(node.getRight());
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
        preOrderRecursively(root.getLeft());
        preOrderRecursively(root.getRight());
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
                cur = cur.getLeft();
            } else {
                cur = stack.pop().getRight();
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
        inOrderRecursively(root.getLeft());
        root.visit();
        inOrderRecursively(root.getRight());
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
                cur = cur.getLeft();
            } else {
                TreeNode node = stack.pop();
                node.visit();
                cur = node.getRight();
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
        postOrderRecursively(root.getLeft());
        postOrderRecursively(root.getRight());
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
                cur = cur.getLeft();
            } else {
                TreeNode node = stack.peek();
                if (!node.hasRightChild() || node.getRight() == lastVisit) {
                    node.visit();
                    lastVisit = node;
                    stack.pop();
                    cur = null;
                } else {
                    cur = node.getRight();
                }
            }
        }
    }

}
