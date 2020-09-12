package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 *
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/12 15:59
 */
public class Topic106 {
    /**
     * 递归法
     */
    public static class Solution1 {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            Map<Integer, Integer> indexMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                indexMap.put(inorder[i], i);
            }
            return buildTree(inorder, 0, postorder, 0, inorder.length, indexMap);
        }

        private TreeNode buildTree(int[] inorder, int inorderIndex, int[] postorder, int postorderIndex, int length, Map<Integer, Integer> indexMap) {
            if (length == 0) {
                return null;
            }
            int rootVal = postorder[postorderIndex + length - 1];
            TreeNode root = new TreeNode(rootVal);
            int inorderRootIndex = indexMap.get(rootVal);
            int leftChildLength = inorderRootIndex - inorderIndex;
            root.left = buildTree(inorder, inorderIndex, postorder, postorderIndex, leftChildLength, indexMap);
            root.right = buildTree(inorder, inorderRootIndex + 1, postorder, postorderIndex + leftChildLength, length - leftChildLength - 1, indexMap);
            return root;
        }
    }

    /**
     * 迭代法
     */
    public static class Solution2 {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            if (inorder.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(postorder[postorder.length - 1]);
            Stack<TreeNode> stack = new Stack<>();
            stack.add(root);
            int index = postorder.length - 1;
            for (int i = postorder.length - 2; i >= 0; i--) {
                TreeNode prev = stack.peek();
                if (inorder[index] != prev.val) {
                    prev.right = new TreeNode(postorder[i]);
                    stack.push(prev.right);
                } else {
                    while (!stack.isEmpty() && stack.peek().val == inorder[index]) {
                        prev = stack.pop();
                        index--;
                    }
                    prev.left = new TreeNode(postorder[i]);
                    stack.add(prev.left);
                }
            }
            return root;
        }
    }
}

