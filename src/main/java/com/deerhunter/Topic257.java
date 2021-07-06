package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * <p>
 * 输出: ["1->2->5", "1->3"]
 * <p>
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 * 通过次数123,487提交次数182,442
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/6/21 3:02 PM
 */
public class Topic257 {
    public static class Solution {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> paths = new ArrayList<>();
            binaryTreePaths(root, new StringBuilder(), paths);
            return paths;
        }

        private void binaryTreePaths(TreeNode node, StringBuilder sb, List<String> paths) {
            if (node == null) {
                return;
            }
            if (sb.length() > 0) {
                sb.append("->");
            }
            sb.append(node.val);
            if (node.left == null && node.right == null) {
                paths.add(sb.toString());
                return;
            }
            int len = sb.length();
            binaryTreePaths(node.left, sb, paths);
            sb.delete(len, sb.length());
            binaryTreePaths(node.right, sb, paths);
            sb.delete(len, sb.length());
        }
    }
}
