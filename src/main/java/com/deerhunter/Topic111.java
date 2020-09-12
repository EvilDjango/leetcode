package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最小深度  2.
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/13 00:53
 */
public class Topic111 {
    class Solution1 {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            if (root.left == null && root.right == null) {
                return 1;
            }
            int minDepth = Integer.MAX_VALUE;
            if (root.left != null) {
                minDepth = Math.min(minDepth, minDepth(root.left));
            }
            if (root.right != null) {
                minDepth = Math.min(minDepth, minDepth(root.right));
            }

            return minDepth + 1;
        }

    }

    class Solution2 {
        private int minHeight;

        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            minHeight = Integer.MAX_VALUE;
            getMinDepth(root, 0);
            return minHeight;
        }

        private void getMinDepth(TreeNode root, int height) {
            if (root == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                minHeight = Math.min(minHeight, height + 1);
            }
            getMinDepth(root.left, height + 1);
            getMinDepth(root.right, height + 1);
        }
    }

    /**
     * 广度优先遍历，参考官方题解
     */
    class Solution3 {
        private int minHeight;

        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            queue.add(null);
            int height = 1;
            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                if (node != null) {
                    if (node.left == null && node.right == null) {
                        return height;
                    }
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                } else {
                    height++;
                    queue.add(null);
                }
            }
            return 0;
        }
    }
}
