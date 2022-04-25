package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 * 2
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * 输出: 4
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 ≤ k ≤ 二叉搜索树元素个数
 * 通过次数224,848提交次数294,978
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/25 下午3:18
 */
public class Offer054 {
    private int index;
    private Integer target;

    /**
     * 右根左遍历，递归版
     *
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        dfs(root, k);
        return target;
    }

    private void dfs(TreeNode root, int k) {
        if (root == null || target != null) {
            return;
        }
        dfs(root.right, k);
        index++;
        if (index == k) {
            target = root.val;
        }
        dfs(root.left, k);
    }

    /**
     * 右根左遍历，循环版
     *
     * @param root
     * @param k
     * @return
     */
    public int kthLargest2(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        int index = 0;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.right;
            } else {
                cur = stack.pop();
                index++;
                if (index == k) {
                    return cur.val;
                }
                cur = cur.left;
            }
        }
        return 0;
    }
}
