package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 226. 翻转二叉树
 * 翻转一棵二叉树。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * 输出：
 * <p>
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 * <p>
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 * 通过次数252,984提交次数322,226
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 6/28/21 2:39 PM
 */
public class Topic226 {
    /**
     * 递归
     */
    public static class Solution1 {
        public TreeNode invertTree(TreeNode root) {
            if (root != null) {
                TreeNode temp = root.left;
                root.left = invertTree(root.right);
                root.right = invertTree(temp);
            }
            return root;
        }
    }

    /**
     * 循环
     */
    public static class Solution2 {
        public TreeNode invertTree(TreeNode root) {
            TreeNode node = root;
            Deque<TreeNode> stack = new ArrayDeque<>();
            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    TreeNode temp = node.left;
                    node.left = node.right;
                    node.right = temp;
                    node = node.left;
                } else {
                    node = stack.pop();
                    node = node.right;
                }
            }
            return root;
        }
    }
}
