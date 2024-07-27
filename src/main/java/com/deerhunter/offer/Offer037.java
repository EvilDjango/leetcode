package com.deerhunter.offer;

import com.deerhunter.tree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer 37. 序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * <p>
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * <p>
 * <p>
 * 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 * <p>
 * 通过次数102,675提交次数180,436
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/3 上午10:39
 */
public class Offer037 {
    /**
     * 层序遍历
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                sb.append(nodeString(node));
                sb.append(",");
                if (node != null) {
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] nodes = data.split(",");
            TreeNode root = parseNode(nodes[0]);
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            for (int i = 1; i < nodes.length; i += 2) {
                TreeNode left = parseNode(nodes[i]);
                TreeNode right = parseNode(nodes[i + 1]);
                TreeNode parent = queue.remove();
                parent.left = left;
                parent.right = right;
                if (left != null) {
                    queue.add(left);
                }
                if (right != null) {
                    queue.add(right);
                }
            }
            return root;
        }

        private String nodeString(TreeNode node) {
            if (node == null) {
                return "X";
            }
            return String.valueOf(node.val);
        }

        private TreeNode parseNode(String s) {
            if ("X".equals(s)) {
                return null;
            }
            return new TreeNode(Integer.parseInt(s));
        }
    }

    /**
     * 括号表示编码 + 递归下降解码
     */
    public class Codec2 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "X";
            }
            return String.format("(%s)%d(%s)", serialize(root.left), root.val, serialize(root.right));
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            int[] index = new int[0];
            return deserialize(data.toCharArray(), index);
        }

        private TreeNode deserialize(char[] data, int[] index) {
            if (data[index[0]] == 'X') {
                index[0]++;
                return null;
            }
            index[0]++;
            TreeNode left = deserialize(data, index);
            index[0]++;
            int base = 1;
            if (data[index[0]] == '-') {
                base = -1;
                index[0]++;
            }
            int val = data[index[0]++] - '0';
            while (Character.isDigit(data[index[0]])) {
                val *= 10;
                val += data[index[0]++] - '0';
            }
            TreeNode node = new TreeNode(base * val);
            node.left = left;
            index[0]++;
            node.right = deserialize(data, index);
            index[0]++;
            return node;
        }
    }

    /**
     * 前序遍历，递归
     */
    public class Codec3 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }

        private void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("X");
                return;
            }
            sb.append(root.val);
            sb.append(",");
            serialize(root.left, sb);
            sb.append(",");
            serialize(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return deserialize(data.split(","), new int[1]);
        }

        private TreeNode deserialize(String[] data, int[] ptr) {
            if ("X".equals(data[ptr[0]])) {
                ptr[0]++;
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(data[ptr[0]++]));
            root.left = deserialize(data, ptr);
            root.right = deserialize(data, ptr);
            return root;
        }
    }
}
