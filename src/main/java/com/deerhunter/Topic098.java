package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.Stack;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/7 12:13
 */
public class Topic098 {
    /**
     * 递归
     */
    public static class Solution1 {
        public static boolean isValidBST(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        public static boolean isValidBST(TreeNode root, long min, long max) {
            if (root == null) {
                return true;
            }
            if (root.val <= min || root.val >= max) {
                return false;
            }
            return isValidBST(root.left, min, Math.min(root.val, max)) && isValidBST(root.right, Math.max(min, root.val), max);

        }
    }

    /**
     * 中序遍历:递归
     */
    public static class Solution2 {
        public static boolean isValidBST(TreeNode root) {
            return inorderTraverse(root, new Integer[1]);
        }

        private static boolean inorderTraverse(TreeNode root, Integer[] max) {
            if (root == null) {
                return true;
            }

            if (!inorderTraverse(root.left, max)) {
                return false;
            }
            if (max[0] != null && root.val <= max[0]) {
                return false;
            }
            max[0] = root.val;
            if (!inorderTraverse(root.right, max)) {
                return false;
            }
            return true;
        }


    }

    /**
     * 中序遍历:递归
     */
    public static class Solution3 {
        public static boolean isValidBST(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            Integer lastVal = null;
            TreeNode cur = root;
            while (cur != null || !stack.isEmpty()) {
                if (cur != null) {
                    stack.add(cur);
                    cur = cur.left;
                } else {
                    TreeNode node = stack.pop();
                    if (lastVal != null && node.val <= lastVal) {
                        return false;
                    }
                    lastVal = node.val;
                    cur = node.right;
                }
            }
            return true;
        }
    }
}
