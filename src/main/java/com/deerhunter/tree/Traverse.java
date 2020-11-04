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
     * 前序遍历：循环 最优解法
     *
     * @param root
     */
    public static void preOrderByLoop(TreeNode root) {
        // 待访问的右孩子
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        TreeNode cur;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            while (cur != null) {
                cur.visit();
                stack.add(cur.right);
                cur = cur.left;
            }
        }
    }

    /**
     * 前序遍历：循环2
     *
     * @param root
     */
    public static void preOrderByLoop2(TreeNode root) {
        // 待访问的右孩子
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                cur.visit();
                stack.add(cur.right);
                cur = cur.getLeft();
            } else {
                cur = stack.pop();
            }
        }
    }

    /**
     * 前序遍历：循环3
     *
     * @param root
     */
    public static void preOrderByLoop3(TreeNode root) {
        // 已经访问过的节点
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                cur.visit();
                stack.add(cur);
                cur = cur.getLeft();
            } else {
                cur = stack.pop().right;
            }
        }
    }

    /**
     * Morris 前序遍历
     *
     * @param root
     */
    public static void preOrderByMorris(TreeNode root) {
        TreeNode predecessor;
        while (root != null) {
            if (root.left == null) {
                root.visit();
                root = root.right;
            } else {
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                // 左子树还没有访问过
                if (predecessor.right == null) {
                    root.visit();
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
     * 中序遍历：循环2
     *
     * @param root
     */
    public static void inOrderByLoop2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                cur.visit();
                stack.add(cur.right);
            }
        }
    }

    /**
     * Morris中序遍历
     *
     * @param root
     */
    public static void inOrderByMorris(TreeNode root) {
        TreeNode predecessor;
        while (root != null) {
            if (root.left == null) {
                root.visit();
                root = root.right;
            } else {
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                // 左子树还没有访问过
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                } else {
                    root.visit();
                    predecessor.right = null;
                    root = root.right;
                }
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

    /**
     * 后序遍历：循环
     *
     * @param root
     */
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
                } else {
                    cur = node.getRight();
                }
            }
        }
    }

    /**
     * 后序遍历：循环2
     *
     * @param root
     */
    public static void postOrderByLoop2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode lastVisit = null;
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                TreeNode node = stack.peek();
                if (node.right == null || node.right == lastVisit) {
                    stack.pop().visit();
                    lastVisit = node;
                    stack.add(null);
                } else {
                    stack.add(node.right);
                }
            }
        }
    }

    /**
     * 莫里斯后序遍历
     *
     * @param root
     */
    public static void postOrderMorris(TreeNode root) {
        TreeNode dumb = new TreeNode(0);
        dumb.left = root;
        root = dumb;
        TreeNode predecessor;
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                // 左子树还没有访问过
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                } else {
                    reverseVisit(root.left, predecessor);
                    predecessor.right = null;
                    root = root.right;
                }
            }
        }
    }

    private static void reverseVisit(TreeNode from, TreeNode to) {
        reverseTreeByRight(from, to);
        TreeNode cur = to;
        while (true) {
            cur.visit();
            if (cur == from) {
                break;
            }
            cur = cur.right;
        }
        reverseTreeByRight(to, from);
    }

    private static void reverseTreeByRight(TreeNode from, TreeNode to) {
        if (from == null) {
            return;
        }
        TreeNode cur = from;
        TreeNode next = from.right;
        TreeNode temp;
        while (cur != to) {
            temp = next.right;
            next.right = cur;
            cur = next;
            next = temp;
        }
    }
}
