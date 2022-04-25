package com.deerhunter;

import com.deerhunter.tree.TreeNode;

/**
 * 剑指 Offer 55 - II. 平衡二叉树
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回 true 。
 * <p>
 * 示例 2:
 * <p>
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 * 1
 * / \
 * 2   2
 * / \
 * 3   3
 * / \
 * 4   4
 * 返回 false 。
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= 树的结点个数 <= 10000
 * 注意：本题与主站 110 题相同：https://leetcode-cn.com/problems/balanced-binary-tree/
 * <p>
 * <p>
 * <p>
 * 通过次数186,902提交次数314,420
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/25 下午2:48
 */
public class Offer055B {
    public boolean isBalanced(TreeNode root) {
        int[] depth = depth(root);
        return Math.abs(depth[0] - depth[1]) > 1;
    }

    private int[] depth(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = depth(root.left);
        if (Math.abs(left[0] - left[1]) > 1) {
            return new int[]{0, 2};
        }
        int[] right = depth(root.right);
        if (Math.abs(right[0] - right[1]) > 1) {
            return new int[]{0, 2};
        }
        return new int[]{Math.max(left[0], left[1]), Math.max(right[0], right[1])};
    }

    /**
     * 参考官方题解
     *
     * @param root
     * @return
     */
    public boolean isBalanced2(TreeNode root) {
        return height(root) >= 0;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
