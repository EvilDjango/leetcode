package com.deerhunter.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * Oops, forgot to write comments. Good luck, bro.
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/11/3 14:46
 */
public class Traverse2 {
    /**
     * 前序遍历：递归
     *
     * @param root
     * @param visitor
     */
    public static void preorderByRecursion(TreeNode root, Consumer<TreeNode> visitor) {
        if (root == null) {
            return;
        }
        visitor.accept(root);
        preorderByRecursion(root.left, visitor);
        preorderByRecursion(root.right, visitor);
    }

    /**
     * 前序遍历：循环
     *
     * @param root
     * @param visitor
     */
    public static void preorderByLoop(TreeNode root, Consumer<TreeNode> visitor) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (root != null || !deque.isEmpty()) {
            if (root != null) {
                visitor.accept(root);
                if (root.right != null) {
                    deque.addLast(root.right);
                }
                root = root.left;
            } else {
                root = deque.removeLast();
            }
        }
    }

    /**
     * 前序遍历：莫里斯
     *
     * @param root
     * @param visitor
     */
    public static void preorderByMorris(TreeNode root, Consumer<TreeNode> visitor) {
        while (root != null) {
            if (root.left == null) {
                visitor.accept(root);
                root = root.right;
            } else {
                TreeNode predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    visitor.accept(root);
                    predecessor.right = root;
                    root = root.left;
                } else {
                    predecessor.right = null;
                    root = root.right;
                }
            }
        }
    }


    /**
     * 中序遍历：递归
     *
     * @param root
     * @param visitor
     */
    public static void inorderByRecursion(TreeNode root, Consumer<TreeNode> visitor) {
        if (root == null) {
            return;
        }
        inorderByRecursion(root.left, visitor);
        visitor.accept(root);
        inorderByRecursion(root.right, visitor);
    }

    /**
     * 中序遍历：循环
     *
     * @param root
     * @param visitor
     */
    public static void inorderByLoop(TreeNode root, Consumer<TreeNode> visitor) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (root != null || !deque.isEmpty()) {
            if (root != null) {
                deque.addLast(root);
                root = root.left;
            } else {
                root = deque.removeLast();
                visitor.accept(root);
                root = root.right;
            }
        }
    }

    public static void inorderByMorris(TreeNode root, Consumer<TreeNode> visitor) {
        while (root != null) {
            if (root.left == null) {
                visitor.accept(root);
                root = root.right;
            } else {
                TreeNode predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                // 第一次访问root节点
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;

                    //第二次访问root节点
                } else {
                    visitor.accept(root);
                    predecessor.right = null;
                    root = root.right;
                }
            }
        }
    }

    public static void postorderByRecursion(TreeNode root, Consumer<TreeNode> visitor) {
        if (root == null) {
            return;
        }
        postorderByRecursion(root.left, visitor);
        postorderByRecursion(root.right, visitor);
        visitor.accept(root);
    }

    public static void postorderByLoop(TreeNode root, Consumer<TreeNode> visitor) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode lastVisit = null;
        while (root != null || !deque.isEmpty()) {
            if (root != null) {
                deque.addLast(root);
                root = root.left;
                System.out.println();
            } else {
                root = deque.peekLast();
                if (root.right == null || root.right == lastVisit) {
                    visitor.accept(root);
                    lastVisit = root;
                    deque.removeLast();
                    root = null;
                } else {
                    root = root.right;
                }
            }
        }
    }

    public static void postorderByMorris(TreeNode root, Consumer<TreeNode> visitor) {
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        root = dummy;
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                TreeNode predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                } else {
                    predecessor.right = null;
                    reverseVisit(root.left, predecessor, visitor);
                    root = root.right;
                }
            }
        }
    }

    private static void reverseVisit(TreeNode from, TreeNode to, Consumer<TreeNode> visitor) {
        reverseTreeByRight(from, to);
        TreeNode cur = to;
        while (cur != from.right) {
            visitor.accept(cur);
            cur = cur.right;
        }
        reverseTreeByRight(to, from);
    }

    private static void reverseTreeByRight(TreeNode from, TreeNode to) {
        TreeNode prev = null;
        TreeNode next;
        TreeNode successor = to.right;
        while (from != successor) {
            next = from.right;
            from.right = prev;
            prev = from;
            from = next;
        }
    }

}
