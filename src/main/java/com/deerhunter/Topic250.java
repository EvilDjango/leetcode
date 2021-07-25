package com.deerhunter;

import com.deerhunter.tree.TreeNode;

/**
 * 250. 统计同值子树
 * 给定一个二叉树，统计该二叉树数值相同的子树个数。
 * <p>
 * 同值子树是指该子树的所有节点都拥有相同的数值。
 * <p>
 * 示例：
 * <p>
 * 输入: root = [5,1,5,5,5,null,5]
 * <p>
 * 5
 * / \
 * 1   5
 * / \   \
 * 5   5   5
 * <p>
 * 输出: 4
 * 通过次数4,037提交次数6,384
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/23/21 1:44 PM
 */
public class Topic250 {
    public static class Solution1 {
        public int countUnivalSubtrees(TreeNode root) {
            int[] ans = new int[1];
            isUnival(root, ans);
            return ans[0];
        }

        private boolean isUnival(TreeNode root, int[] ans) {
            if (root == null) {
                return true;
            }
            TreeNode left = root.left, right = root.right;
            if ((left == null || left.val == root.val) & (right == null || right.val == root.val) & isUnival(left, ans) & isUnival(right, ans)) {
                ans[0]++;
                return true;
            }
            return false;
        }
    }

    public static class Solution2 {
        public int countUnivalSubtrees(TreeNode root) {
            int[] ans = new int[1];
            isUnivalPart(root, 0, ans);
            return ans[0];
        }

        private boolean isUnivalPart(TreeNode root, int ancestor, int[] ans) {
            if (root == null) {
                return true;
            }
            if (!isUnivalPart(root.left, root.val, ans) | !isUnivalPart(root.right, root.val, ans)) {
                return false;
            }
            ans[0]++;
            return root.val == ancestor;
        }
    }
}
