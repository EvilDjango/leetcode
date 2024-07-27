package com.deerhunter.topic;

import com.deerhunter.tree.TreeNode;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定有序数组: [-10,-3,0,5,9],
 * <p>
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/12 21:49
 */
public class Topic108 {
    public static class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            return buildBst(nums, 0, nums.length);
        }

        private TreeNode buildBst(int[] nums, int left, int right) {
            if (left == right) {
                return null;
            }
            int middle = left + (right - left - 1) / 2;
            TreeNode root = new TreeNode(nums[middle]);
            root.left = buildBst(nums, left, middle);
            root.right = buildBst(nums, middle + 1, right);
            return root;
        }
    }
}
