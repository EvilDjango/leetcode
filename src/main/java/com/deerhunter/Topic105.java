package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/12 12:08
 */
public class Topic105 {
    /**
     * 递归解法
     */
    public static class Solution1 {
        public static TreeNode buildTree(int[] preorder, int[] inorder) {
            return buildTree(preorder, 0, preorder.length, inorder, 0);
        }

        static TreeNode buildTree(int[] preorder, int preorderLeft, int preorderRight, int[] inorder, int inorderLeft) {
            int length = preorderRight - preorderLeft;
            if (length == 0) {
                return null;
            }
            if (length == 1) {
                return new TreeNode(preorder[preorderLeft]);
            }
            // 前序遍历的首节点是根节点
            int rootVal = preorder[preorderLeft];
            TreeNode root = new TreeNode(rootVal);
            // 中序遍历中的根节点下标
            int inorderRootIndex = inorderLeft;
            while (inorder[inorderRootIndex] != rootVal) {
                inorderRootIndex++;
            }
            // 左子树的节点数
            int leftChildLength = inorderRootIndex - inorderLeft;
            // 前序遍历中的左侧子树边界
            int preorderLeftBoundary = preorderLeft + leftChildLength + 1;
            root.left = buildTree(preorder, preorderLeft + 1, preorderLeftBoundary, inorder, inorderLeft);
            root.right = buildTree(preorder, preorderLeftBoundary, preorderRight, inorder, inorderRootIndex + 1);
            return root;
        }

    }

    /**
     * 比较机械的迭代解法，相当于把使用递归的解法1翻译成了迭代版本
     */
    public static class Solution2 {
        public static TreeNode buildTree(int[] preorder, int[] inorder) {
            int length = preorder.length;
            if (length == 0) {
                return null;
            }
            List<TreeNode> nodes = new ArrayList<>();
            Queue<int[]> children = new ArrayDeque<>();
            children.add(new int[]{-1, -1, 0, 0, length});
            while (!children.isEmpty()) {
                int[] child = children.remove();
                TreeNode node = new TreeNode(preorder[child[2]]);
                if (child[0] != -1) {
                    TreeNode ancestor = nodes.get(child[0]);
                    if (child[1] == 0) {
                        ancestor.left = node;
                    } else {
                        ancestor.right = node;
                    }
                }
                nodes.add(node);
                int[][] directChildren = getDirectChildren(nodes.size() - 1, preorder, child[2], inorder, child[3], child[4]);
                if (directChildren[0] != null) {
                    children.add(directChildren[0]);
                }
                if (directChildren[1] != null) {
                    children.add(directChildren[1]);
                }
            }
            return nodes.get(0);
        }

        private static int[][] getDirectChildren(int ancestorIndex, int[] preorder, int preorderLeft, int[] inorder, int inorderLeft, int length) {
            int root = preorder[preorderLeft];
            int inorderRootIndex = inorderLeft;
            while (inorder[inorderRootIndex] != root) {
                inorderRootIndex++;
            }
            int leftLength = inorderRootIndex - inorderLeft;
            int rightLength = length - 1 - leftLength;
            int preorderLeftChildBoundary = preorderLeft + 1 + leftLength;
            // 第二位为0表示左分支，为1表示右分支
            int[] leftChild = leftLength == 0 ? null : new int[]{ancestorIndex, 0, preorderLeft + 1, inorderLeft, leftLength};
            int[] rightChild = rightLength == 0 ? null : new int[]{ancestorIndex, 1, preorderLeftChildBoundary, inorderRootIndex + 1, rightLength};
            return new int[][]{
                    leftChild,
                    rightChild
            };
        }
    }

    /**
     * 迭代解法，参考官方题解
     */
    public static class Solution3 {
        public static TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[0]);
            Stack<TreeNode> stack = new Stack<>();
            stack.add(root);
            int index = 0;
            for (int i = 1; i < preorder.length; i++) {
                TreeNode prev = stack.peek();
                if (prev.val != inorder[index]) {
                    prev.left = new TreeNode(preorder[i]);
                    stack.add(prev.left);
                } else {
                    while (!stack.isEmpty()&&inorder[index] == stack.peek().val) {
                        index++;
                        prev = stack.pop();
                    }
                    prev.right = new TreeNode(preorder[i]);
                    stack.add(prev.right);
                }
            }
            return root;
        }
    }
}
