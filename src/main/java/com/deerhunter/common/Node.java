package com.deerhunter.common;

import com.deerhunter.tree.TreeNode;

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public static Node fromTreeNode(TreeNode tree) {
        if (tree == null) {
            return null;
        }
        Node root = new Node(tree.val);
        root.left = fromTreeNode(tree.left);
        root.right = fromTreeNode(tree.right);
        return root;
    }
}
