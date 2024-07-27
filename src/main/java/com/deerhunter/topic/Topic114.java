package com.deerhunter.topic;

import com.deerhunter.tree.TreeNode;

import java.util.Deque;
import java.util.Stack;

/**
 * 给定一个二叉树，原地将它展开为一个单链表。
 * <p>
 *  
 * <p>
 * 例如，给定二叉树
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * 将其展开为：
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/14 13:56
 */
public class Topic114 {
    public static class Solution1 {
        private TreeNode prev;

        public void flatten(TreeNode root) {
            prev = null;
            doFlatten(root);
        }

        private void doFlatten(TreeNode root) {
            if (root == null) {
                return;
            }
            doFlatten(root.right);
            doFlatten(root.left);
            if (prev != null) {
                root.right = prev;
            }
            root.left = null;
            prev = root;
        }
    }

    /**
     * 迭代法
     */
    public static class Solution2 {
        public void flatten(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode prev = null;
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    stack.add(root.right);
                    root.right = root.left;
                    root.left = null;
                    prev = root;
                    root = root.right;
                } else {
                    root = stack.pop();
                    prev.right = root;
                }
            }
        }

    }

    /**
     * Morris遍历
     */
    public static class Solution3 {
        public static void flatten(TreeNode root) {
            TreeNode predecessor;
            while (root != null) {
                if (root.left != null) {
                    predecessor = root.left;
                    while (predecessor.right != null) {
                        predecessor = predecessor.right;
                    }
                    predecessor.right = root.right;
                    root.right = root.left;
                    root.left = null;
                }
                root = root.right;
            }
        }
    }
}
