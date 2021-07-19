package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 156. 上下翻转二叉树
 * 给定一个二叉树，其中所有的右节点要么是具有兄弟节点（拥有相同父节点的左节点）的叶节点，要么为空，将此二叉树上下翻转并将它变成一棵树， 原来的右节点将转换成左叶节点。返回新的根。
 * <p>
 * 例子:
 * <p>
 * 输入: [1,2,3,4,5]
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * 输出: 返回二叉树的根 [4,5,2,#,#,3,1]
 * <p>
 * 4
 * / \
 * 5   2
 * / \
 * 3   1
 * 说明:
 * <p>
 * 对 [4,5,2,#,#,3,1] 感到困惑? 下面详细介绍请查看 二叉树是如何被序列化的。
 * <p>
 * 二叉树的序列化遵循层次遍历规则，当没有节点存在时，'#' 表示路径终止符。
 * <p>
 * 这里有一个例子:
 * <p>
 * 1
 * / \
 * 2   3
 * /
 * 4
 * \
 * 5
 * 上面的二叉树则被序列化为 [1,2,3,#,#,4,#,#,5].
 * <p>
 * 通过次数5,565提交次数7,639
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/15/21 10:36 AM
 */
public class Topic156 {
    /**
     * 循环
     */
    public static class Solution1 {
        public TreeNode upsideDownBinaryTree(TreeNode root) {
            TreeNode ancestor = null;
            TreeNode sibling = null;
            while (root != null) {
                TreeNode left = root.left;
                root.left = sibling;
                sibling = root.right;
                root.right = ancestor;
                ancestor = root;
                root = left;
            }
            return ancestor;
        }
    }

    /**
     * 递归
     */
    public static class Solution2 {
        public TreeNode upsideDownBinaryTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            return upsideDownBinaryTree(root, null, null);
        }

        private TreeNode upsideDownBinaryTree(TreeNode node, TreeNode ancestor, TreeNode sibling) {
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = sibling;
            node.right = ancestor;
            return left == null ? node : upsideDownBinaryTree(left, node, right);
        }
    }
}
