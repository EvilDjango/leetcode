package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 * <p>
 * <p>
 * 输入：root = [1,2]
 * 输出：[1,2]
 * 示例 5：
 * <p>
 * <p>
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *  
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *  
 * <p>
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/10/30 17:06
 */
public class Topic144 {
    /**
     * 递归
     */
    public static class Solution1 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            preorderTraversal(root, result);
            return result;
        }

        private void preorderTraversal(TreeNode root, List<Integer> result) {
            if (root == null) {
                return;
            }
            result.add(root.val);
            preorderTraversal(root.left, result);
            preorderTraversal(root.right, result);
        }
    }

    /**
     * 循环
     */
    public static class Solution2 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            Deque<TreeNode> queue = new ArrayDeque<>();
            while (!queue.isEmpty() || root != null) {
                if (root != null) {
                    result.add(root.val);
                    if (root.right != null) {
                        queue.addLast(root.right);
                    }
                    root = root.left;
                } else {
                    root = queue.removeLast();
                }
            }
            return result;
        }
    }

    /**
     * 莫里斯前序遍历
     */
    public static class Solution3 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            while (root != null) {
                if (root.left == null) {
                    result.add(root.val);
                    root = root.right;
                } else {
                    TreeNode rightest = root.left;
                    while (rightest.right != null && rightest.right != root) {
                        rightest = rightest.right;
                    }
                    if (rightest.right == null) {
                        result.add(root.val);
                        rightest.right = root;
                        root = root.left;
                    } else {
                        rightest.right = null;
                        root = root.right;
                    }
                }
            }
            return result;
        }
    }
}
