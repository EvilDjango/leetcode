package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 *  
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 *  
 * <p>
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 *  
 * <p>
 * 进阶：
 * <p>
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/11 17:10
 */
public class Topic101 {
    /**
     * 递归
     */
    public static class Solution1 {
        public static boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return isSymmetric(root.left, root.right);
        }

        private static boolean isSymmetric(TreeNode left, TreeNode right) {
            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
        }
    }

    /**
     * 循环
     */
    public static class Solution2 {
        public static boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            Queue<TreeNode> leftQueue = new LinkedList<>();
            leftQueue.add(root.left);
            Queue<TreeNode> rightQueue = new LinkedList<>();
            rightQueue.add(root.right);
            while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
                TreeNode left = leftQueue.remove();
                TreeNode right = rightQueue.remove();
                if (left == null ^ right == null) {
                    return false;
                }
                if (left != null) {
                    if (left.val != right.val) {
                        return false;
                    }
                    leftQueue.add(left.left);
                    leftQueue.add(left.right);
                    rightQueue.add(right.right);
                    rightQueue.add(right.left);
                }
            }
            return leftQueue.size() == rightQueue.size();
        }
    }
}
