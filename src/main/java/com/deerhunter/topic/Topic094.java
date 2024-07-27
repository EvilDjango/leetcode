package com.deerhunter.topic;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/4/25 14:28
 */
public class Topic094 {

    /**
     * 递归法
     */
    public static class Solution1 {
        private List<Integer> results;

        public List<Integer> inorderTraversal(TreeNode root) {
            results = new ArrayList<>();
            doInorderTraversal(root);
            return results;
        }

        private void doInorderTraversal(TreeNode root) {
            if (root == null) {
                return;
            }
            doInorderTraversal(root.left);
            results.add(root.val);
            doInorderTraversal(root.right);
        }
    }

    /**
     * 迭代法:会改变原二叉树
     */
    public static class Solution2 {

        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> results = new ArrayList<>();
            if (null == root) {
                return results;
            }
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.peek();
                if (node.left != null) {
                    stack.push(node.left);
                    node.left = null;
                } else {
                    results.add(node.val);
                    stack.pop();
                    if (node.right != null) {
                        stack.push(node.right);
                    }
                }
            }
            return results;
        }
    }

    /**
     * 迭代法。不改变原二叉树
     */
    public static class Solution3 {

        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> results = new ArrayList<>();
            if (null == root) {
                return results;
            }
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.peek();

                // 左节点不为空，需要一直拓展
                while (node.left != null) {
                    stack.push(node.left);
                    node = node.left;
                }

                // 左节点为空，将当前节点加入结果集
                results.add(node.val);
                stack.pop();

                // 右节点为空，则弹出父节点
                while (node.right == null && !stack.isEmpty()) {
                    node = stack.pop();
                    results.add(node.val);
                }

                // 右节点不为空，将右节点加入队列
                if (node.right != null) {
                    stack.add(node.right);
                }

            }
            return results;
        }
    }

    /**
     * 迭代法：标准的简洁写法
     */
    public static class Solution4 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> results = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    TreeNode ancestor = stack.pop();
                    results.add(ancestor.val);
                    root = ancestor.right;
                }
            }
            return results;
        }
    }

    /**
     * 莫里斯方法，会改变原二叉树
     */
    public static class Solution5 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> results = new ArrayList<>();
            TreeNode pre;
            TreeNode cur = root;
            while (cur != null) {
                if (cur.left == null) {
                    results.add(cur.val);
                    cur = cur.right;
                } else {
                    pre = cur.left;
                    while (pre.right != null) {
                        pre = pre.right;
                    }
                    pre.right = cur;
                    TreeNode temp = cur;
                    cur = cur.left;
                    temp.left = null;
                }
            }
            return results;
        }
    }

    /**
     * 莫里斯方法。不改变原二叉树。
     */
    public static class Solution6 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> results = new ArrayList<>();
            TreeNode pre;
            TreeNode cur = root;
            while (cur != null) {
                if (cur.left == null) {
                    results.add(cur.val);
                    cur = cur.right;
                } else {
                    pre = cur.left;
                    while (pre.right != null && pre.right != cur) {
                        pre = pre.right;
                    }
                    if (pre.right == null) {
                        pre.right = cur;
                        cur = cur.left;
                    } else {
                        // 遇到环，在这里断开环，保持原有的树结构
                        pre.right = null;
                        results.add(cur.val);
                        cur = cur.right;
                    }

                }
            }
            return results;
        }
    }
}
