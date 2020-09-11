package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.Stack;

/**
 * 二叉搜索树中的两个节点被错误地交换。
 * <p>
 * 请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,null,null,2]
 * <p>
 *    1
 *   /
 *  3
 *   \
 *    2
 * <p>
 * 输出: [3,1,null,null,2]
 * <p>
 *    3
 *   /
 *  1
 *   \
 *    2
 * 示例 2:
 * <p>
 * 输入: [3,1,4,null,null,2]
 * <p>
 * 3
 * / \
 * 1   4
 *    /
 *   2
 * <p>
 * 输出: [2,1,4,null,null,3]
 * <p>
 * 2
 * / \
 * 1   4
 *    /
 *  3
 * 进阶:
 * <p>
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/9 10:35
 */
public class Topic099 {
    /**
     * 递归，中序遍历，交换两个节点
     */
    public static class Solution1 {
        private static TreeNode left;
        private static TreeNode right;
        private static TreeNode lastVisit;
        private static TreeNode lastVisitAncestor;
        private static TreeNode leftAncestor;
        private static TreeNode rightAncestor;
        private static Integer max;

        public static TreeNode recoverTree(TreeNode root) {
            left = null;
            right = null;
            leftAncestor = null;
            rightAncestor = null;
            max = null;
            findWrongNodes(root, null);
            TreeNode.swapNodeInBinarySearchTree(left, leftAncestor, right, rightAncestor);
            if (leftAncestor == null) {
                return right;
            }
            if (rightAncestor == null) {
                return left;
            }
            return root;
        }

        private static void findWrongNodes(TreeNode root, TreeNode ancestor) {
            if (root == null) {
                return;
            }
            findWrongNodes(root.left, root);
            // 查找左节点
            if (left == null && max != null && root.val <= max) {
                left = lastVisit;
                leftAncestor = lastVisitAncestor;
            }
            if (left != null && root.val <= left.val) {
                right = root;
                rightAncestor = ancestor;
            }

            max = root.val;
            lastVisit = root;
            lastVisitAncestor = ancestor;
            findWrongNodes(root.right, root);
        }
    }

    /**
     * 循环，中序遍历，只交换了两个节点的值，并没有真正交换两个节点
     */
    public static class Solution2 {
        public static void recoverTree(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode lastVisit = null;
            TreeNode left = null;
            TreeNode right = null;
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    if (lastVisit != null && lastVisit.val >= root.val) {
                        right = root;
                        if (left == null) {
                            left = lastVisit;
                        } else {
                            break;
                        }
                    }

                    lastVisit = root;
                    root = root.right;
                }
            }

            int temp = left.val;
            left.val = right.val;
            right.val = temp;
        }
    }

    /**
     * Morris中序遍历
     */
    public static class Solution3 {
        public static void recoverTree(TreeNode root) {

        }
    }
}
