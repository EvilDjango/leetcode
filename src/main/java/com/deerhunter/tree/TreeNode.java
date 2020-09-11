package com.deerhunter.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 树节点
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-20
 */
public class TreeNode {
    public int val;
    public TreeNode ancestor;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode ancestor) {
        this.val = val;
        this.ancestor = ancestor;
    }

    public TreeNode(int val, TreeNode ancestor, TreeNode left, TreeNode right) {
        this.val = val;
        this.ancestor = ancestor;
        this.left = left;
        this.right = right;
    }

    public TreeNode getAncestor() {
        return ancestor;
    }

    public void setAncestor(TreeNode ancestor) {
        this.ancestor = ancestor;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void visit() {
        System.out.println(val);
    }

    public boolean hasLeftChild() {
        return left != null;
    }

    public boolean hasRightChild() {
        return right != null;
    }

    /**
     * 构造一个完全二叉树
     *
     * @param nums
     * @return
     */
    public static TreeNode createCompleteTree(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return null;
        }
        Queue<TreeNode> queue = new ArrayDeque<>(len);
        TreeNode root = new TreeNode(nums[0]);
        queue.add(root);
        int i = 1;
        while (i < len) {
            TreeNode node = queue.remove();
            TreeNode leftChild = new TreeNode(nums[i++], node);
            node.setLeft(leftChild);
            queue.add(leftChild);
            if (i < len) {
                TreeNode rightChild = new TreeNode(nums[i++], node);
                node.setRight(rightChild);
                queue.add(rightChild);
            }
        }
        return root;
    }

    /**
     * 构造一个二叉树
     *
     * @param nums
     * @return
     */
    public static TreeNode createTree(Integer[] nums) {
        int len = nums.length;
        if (len == 0 || nums[0] == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(nums[0]);
        queue.add(root);
        int i = 1;
        while (i < len) {
            TreeNode node = queue.remove();
            // 空节点没有子节点
            if (node == null) {
                i += 2;
                continue;
            }

            Integer left = nums[i++];
            TreeNode leftChild = left == null ? null : new TreeNode(left, node);
            node.setLeft(leftChild);
            queue.add(leftChild);

            if (i < len) {
                Integer right = nums[i++];
                TreeNode rightChild = right == null ? null : new TreeNode(right, node);
                node.setRight(rightChild);
                queue.add(rightChild);
            }
        }
        return root;
    }

    /**
     * 构造一个二叉树
     *
     * @param nums
     * @return
     */
    public static TreeNode createTree2(Integer[] nums) {
        if (nums.length == 0) {
            return null;
        }
        TreeNode[] nodes = new TreeNode[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == null) {
                continue;
            }
            nodes[i] = new TreeNode(nums[i]);
            if (i > 0) {
                int ancestorIndex = (i - 1) / 2;
                nodes[i].setAncestor(nodes[ancestorIndex]);
                boolean isLeft = (i - 2 * ancestorIndex) % 2 == 1;
                if (isLeft) {
                    nodes[ancestorIndex].left = nodes[i];
                } else {
                    nodes[ancestorIndex].right = nodes[i];
                }
            }
        }
        return nodes[0];
    }

    public TreeNode[] toArray() {
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(this);
        int index = 0;
        int layer = 0;
        int currentLayerNodes = 0;
        int currentLayerNodesTotal = 1;
        boolean currentLayerHasNode = false;

        while (index < nodes.size()) {
            TreeNode node = nodes.get(index++);
            currentLayerNodes++;
            currentLayerHasNode |= node != null;
            if (currentLayerNodes == currentLayerNodesTotal) {
                // 如果当前一整层都为空，停止遍历
                if (!currentLayerHasNode) {
                    break;
                }
                layer++;
                currentLayerHasNode = false;
                currentLayerNodes = 0;
                currentLayerNodesTotal = 1 << layer;
            }
            if (node == null) {
                nodes.add(null);
                nodes.add(null);
            } else {
                nodes.add(node.left);
                nodes.add(node.right);
            }
        }
        //移除尾部的空节点
        while (nodes.get(nodes.size() - 1) == null) {
            nodes.remove(nodes.size() - 1);
        }
        return nodes.toArray(new TreeNode[0]);
    }

    public Integer[] toIntegers() {
        TreeNode[] treeNodes = toArray();
        Integer[] integers = new Integer[treeNodes.length];
        for (int i = 0; i < treeNodes.length; i++) {
            if (treeNodes[i] != null) {
                integers[i] = treeNodes[i].val;
            }
        }
        return integers;
    }


    /**
     * 递归创建二叉树
     *
     * @param nums
     * @return
     */
    public static TreeNode createTreeRecursively(Integer[] nums) {
        return createTreeRecursively(nums, 0);
    }

    /**
     * 创建以nums[rootIndex]为根节点的二叉树
     *
     * @param nums
     * @param rootIndex
     * @return
     */
    private static TreeNode createTreeRecursively(Integer[] nums, int rootIndex) {
        if (rootIndex >= nums.length || nums[rootIndex] == null) {
            return null;
        }
        TreeNode node = new TreeNode(nums[rootIndex]);
        node.left = createTreeRecursively(nums, 2 * rootIndex + 1);
        node.right = createTreeRecursively(nums, 2 * rootIndex + 2);
        return node;
    }

    /**
     * 交换搜索二叉树中的两个节点
     *
     * @param node1
     * @param ancestor1
     * @param node2
     * @param ancestor2
     */
    public static void swapNodeInBinarySearchTree(TreeNode node1, TreeNode ancestor1, TreeNode node2, TreeNode ancestor2) {
        if (node1 == null || node2 == null) {
            return;
        }
        TreeNode temp = new TreeNode(0);
        temp.left = node1.left;
        temp.right = node1.right;

        if (ancestor1 != null) {
            boolean isLeft = ancestor1.left == node1;
            if (isLeft) {
                ancestor1.left = node2;
            } else {
                ancestor1.right = node2;
            }
        }
        if (ancestor2 != null) {
            boolean isLeft = ancestor2.right == node2;
            if (isLeft) {
                ancestor2.left = node1;
            } else {
                ancestor2.right = node1;
            }
        }

        node1.left = node2.left;
        node1.right = node2.right;
        node2.left = temp.left;
        node2.right = temp.right;
    }
}
