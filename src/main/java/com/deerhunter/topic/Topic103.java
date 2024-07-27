package com.deerhunter.topic;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层次遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/11 18:17
 */
public class Topic103 {
    /**
     * 宽度优先搜索
     */
    public static class Solution1 {
        public static List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            LinkedList<TreeNode> nodesOfLayer;
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            boolean leftToRight = true;
            while (!queue.isEmpty()) {
                nodesOfLayer = new LinkedList<>();
                LinkedList<Integer> list = new LinkedList<>();
                while (!queue.isEmpty()) {
                    TreeNode node = queue.remove();
                    nodesOfLayer.add(node);
                    if (leftToRight) {
                        list.addLast(node.val);
                    } else {
                        list.addFirst(node.val);

                    }
                }
                if (!list.isEmpty()) {
                    result.add(list);
                }
                for (TreeNode node : nodesOfLayer) {
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }

                leftToRight = !leftToRight;
            }
            return result;
        }


    }

    /**
     * 深度优先搜索，使用空节点分隔不同层级
     */
    public static class Solution2 {
        public static List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            LinkedList<Integer> layer = new LinkedList<>();
            queue.add(root);
            queue.add(null);
            boolean leftToRight = true;
            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                if (node != null) {
                    if (leftToRight) {
                        layer.addLast(node.val);
                    } else {
                        layer.addFirst(node.val);
                    }
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    // 遇到空节点表示上一层已经遍历完成
                } else {
                    result.add(layer);
                    if (queue.isEmpty()) {
                        break;
                    }
                    layer = new LinkedList<>();
                    queue.add(null);
                    leftToRight = !leftToRight;
                }
            }
            return result;
        }
    }

    /**
     * 递归
     */
    public static class Solution3 {
        public static List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            preOrderVisit(root, 0, true, result);
            return result;
        }

        private static void preOrderVisit(TreeNode root, int height, boolean leftToRight, List<List<Integer>> result) {
            if (root == null) {
                return;
            }
            if (result.size() < height + 1) {
                result.add(new LinkedList<>());
            }
            LinkedList<Integer> list = (LinkedList<Integer>) result.get(height);
            if (leftToRight) {
                list.addLast(root.val);
            } else {
                list.addFirst(root.val);
            }
            preOrderVisit(root.left, height + 1, !leftToRight, result);
            preOrderVisit(root.right, height + 1, !leftToRight, result);

        }
    }
}
