package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 *  
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/11 18:17
 */
public class Topic102 {
    /**
     * 参考题解，宽度优先搜索
     */
    public static class Solution1 {
        public static List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            List<TreeNode> layer;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                layer = new ArrayList<>();
                while (!queue.isEmpty()) {
                    layer.add(queue.remove());
                }
                for (TreeNode node : layer) {
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                result.add(layer.stream().map(node -> node.val).collect(Collectors.toList()));
            }
            return result;
        }
    }

    /**
     * 递归
     */
    public static class Solution2 {
        public static List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            preOrderVisit(root, 0, result);
            return result;
        }

        private static void preOrderVisit(TreeNode root, int height, List<List<Integer>> result) {
            if (root == null) {
                return;
            }
            if (result.size() < height + 1) {
                result.add(new ArrayList<>());
            }
            result.get(height).add(root.val);
            preOrderVisit(root.left, height + 1, result);
            preOrderVisit(root.right, height + 1, result);
        }
    }
}
