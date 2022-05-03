package com.deerhunter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 剑指 Offer 36. 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * <p>
 * <p>
 * <p>
 * 为了让您更好地理解问题，以下面的二叉搜索树为例：
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 * <p>
 * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
 * <p>
 * <p>
 * <p>
 * 注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 * <p>
 * 注意：此题对比原题有改动。
 * <p>
 * 通过次数152,798提交次数234,128
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/3 下午12:42
 */
public class Offer036 {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    /**
     * 循环
     *
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node cur = root;
        Node prev = null, head = null;
        Deque<Node> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                cur.left = prev;
                if (prev != null) {
                    prev.right = cur;
                } else {
                    head = cur;
                }
                prev = cur;
                cur = cur.right;
            }
        }
        head.left = prev;
        prev.right = head;
        return head;
    }

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public Node treeToDoublyList2(Node root) {
        if (root == null) {
            return null;
        }
        // nodes[1]表示前驱结点，nodes[0]表示首节点
        Node[] nodes = new Node[2];
        dfs(root, nodes);
        nodes[0].left = nodes[1];
        nodes[1].right = nodes[0];
        return nodes[0];
    }

    private void dfs(Node root, Node[] nodes) {
        if (root == null) {
            return;
        }
        dfs(root.left, nodes);
        root.left = nodes[1];
        if (nodes[1] == null) {
            nodes[0] = root;
        } else {
            nodes[1].right = root;
        }
        nodes[1] = root;
        dfs(root.right, nodes);
    }
}
