package com.deerhunter;

import com.deerhunter.tree.TreeNode;
import com.sun.tools.javac.util.Pair;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 222. Count Complete Tree Nodes
 * Medium
 * <p>
 * 2845
 * <p>
 * 255
 * <p>
 * Add to List
 * <p>
 * Share
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 * <p>
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 * Example 2:
 * <p>
 * Input: root = []
 * Output: 0
 * Example 3:
 * <p>
 * Input: root = [1]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 5 * 104].
 * 0 <= Node.val <= 5 * 104
 * The tree is guaranteed to be complete.
 * <p>
 * <p>
 * Follow up: Traversing the tree to count the number of nodes in the tree is an easy solution but with O(n) complexity. Could you find a faster algorithm?
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/4/13 17:58
 */
public class Topic222 {

    /**
     * 从右往左扫描最底层节点，第一次发现高度不一致时即可确定最后一层的节点数量
     */
    public static class Solution1 {
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int prevDepth = -1;
            int curDepth = 0;
            int count = 0;
            Queue<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
            stack.add(new Pair<>(root, 1));
            TreeNode node = null;
            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    if (node.right != null) {
                        stack.add(new Pair<>(node.left, curDepth + 1));
                        node = node.right;
                    } else {
                        node = node.left;
                    }
                    if (node == null) {
                        count++;
                        if (prevDepth == -1) {
                            prevDepth = curDepth;
                        } else if (prevDepth != curDepth) {
                            break;
                        }
                    }
                    curDepth++;
                } else {
                    Pair<TreeNode, Integer> pair = stack.remove();
                    node = pair.fst;
                    curDepth = pair.snd;
                }
            }
            return curDepth == prevDepth ? 1 << curDepth - 1 : 1 << curDepth - count;
        }
    }
    
}
