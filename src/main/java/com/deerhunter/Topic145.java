package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/11/4 20:05
 */
public class Topic145 {
    /**
     * 递归
     */
    public static class Solution1 {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            postorderTraversal(root, result);
            return result;
        }

        private void postorderTraversal(TreeNode root, List<Integer> result) {
            if (root == null) {
                return;
            }
            postorderTraversal(root.left, result);
            postorderTraversal(root.right, result);
            result.add(root.val);
        }
    }

    /**
     * 循环
     */
    public static class Solution2 {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            Deque<TreeNode> deque = new ArrayDeque<>();
            TreeNode lastVisit = null;
            while (root != null || !deque.isEmpty()) {
                if (root != null) {
                    deque.addLast(root);
                    root = root.left;
                } else {
                    root = deque.peekLast();
                    if (root.right == null || root.right == lastVisit) {
                        result.add(root.val);
                        lastVisit = root;
                        deque.removeLast();
                        root = null;
                    } else {
                        root = root.right;
                    }
                }
            }
            return result;
        }
    }

    /**
     * 莫里斯
     */
    public static class Solution3 {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            TreeNode dummy = new TreeNode(0);
            dummy.left = root;
            root = dummy;
            while (root != null) {
                if (root.left == null) {
                    root = root.right;
                } else {
                    TreeNode predecessor = root.left;
                    while (predecessor.right != null && predecessor.right != root) {
                        predecessor = predecessor.right;
                    }
                    if (predecessor.right == null) {
                        predecessor.right = root;
                        root = root.left;
                    } else {
                        reverseVisit(root.left, predecessor, result);
                        predecessor.right = null;
                        root = root.right;
                    }
                }
            }
            return result;
        }

        private void reverseVisit(TreeNode from, TreeNode to, List<Integer> values) {
            reverseByRight(from, to);
            TreeNode cur = to;
            while (cur != from.right) {
                values.add(cur.val);
                cur = cur.right;
            }
            reverseByRight(to, from);
        }

        private void reverseByRight(TreeNode from, TreeNode to) {
            TreeNode toNext = to.right;
            TreeNode prev = null;
            while (from != toNext) {
                TreeNode next = from.right;
                from.right = prev;
                prev = from;
                from = next;
            }
        }
    }


}
