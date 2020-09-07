package com.deerhunter;

import com.deerhunter.common.Utils;
import com.deerhunter.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：3
 * 输出：
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 8
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/4 17:11
 */
public class Topic095 {
    public static class Solution1 {
        public static List<TreeNode> generateTrees(int n) {
            return dfs(1, n + 1);
        }

        private static List<TreeNode> dfs(int left, int right) {
            List<TreeNode> trees = new ArrayList<>();
            if (left == right) {
                return trees;
            }
            for (int i = left; i < right; i++) {
                List<TreeNode> leftTrees = dfs(left, i);
                if (leftTrees.size() == 0) {
                    leftTrees.add(null);
                }
                List<TreeNode> rightTrees = dfs(i + 1, right);
                if (rightTrees.size() == 0) {
                    rightTrees.add(null);
                }
                for (TreeNode leftTree : leftTrees) {
                    for (TreeNode rightTree : rightTrees) {
                        TreeNode head = new TreeNode(i);
                        head.left = leftTree;
                        head.right = rightTree;
                        trees.add(head);
                    }
                }
            }
            return trees;
        }
    }

}
