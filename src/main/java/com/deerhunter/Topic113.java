package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * 返回:
 * <p>
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/14 13:32
 */
public class Topic113 {
    public static class Solution {
        private List<List<Integer>> result;

        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            result = new ArrayList<>();
            pathSum(root, sum, new ArrayList<>());
            return result;
        }

        private void pathSum(TreeNode root, int sum, List<Integer> path) {
            if (root == null) {
                return;
            }
            path.add(root.val);
            if (root.left == null && root.right == null) {
                if (sum == root.val) {
                    result.add(new ArrayList<>(path));
                }
            } else {
                pathSum(root.left, sum - root.val, path);
                pathSum(root.right, sum - root.val, path);
            }
            path.remove(path.size() - 1);
        }
    }
}
