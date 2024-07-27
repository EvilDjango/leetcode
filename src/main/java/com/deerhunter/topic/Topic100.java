package com.deerhunter.topic;

import com.deerhunter.tree.TreeNode;

import java.util.Stack;

/**
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * 示例 1:
 * <p>
 * 输入:       1         1
 * / \       / \
 * 2   3     2   3
 * <p>
 * [1,2,3],   [1,2,3]
 * <p>
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:      1          1
 * /           \
 * 2             2
 * <p>
 * [1,2],     [1,null,2]
 * <p>
 * 输出: false
 * 示例 3:
 * <p>
 * 输入:       1         1
 * / \       / \
 * 2   1     1   2
 * <p>
 * [1,2,1],   [1,1,2]
 * <p>
 * 输出: false
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/same-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/11 15:57
 */
public class Topic100 {
    /**
     * 递归
     */
    public static class Solution1 {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if (p == null || q == null) {
                return false;
            }
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    /**
     * 循环
     */
    public static class Solution2 {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();
            while (p != null || !stack1.isEmpty()) {
                if (p != null) {
                    if (q == null) {
                        return false;
                    }
                    if (p.val != q.val) {
                        return false;
                    }
                    stack1.add(p.right);
                    stack2.add(q.right);
                    p = p.left;
                    q = q.left;
                } else {
                    if (q != null) {
                        return false;
                    }
                    p = stack1.pop();
                    q = stack2.pop();
                }
            }
            return q == null;
        }
    }

    /**
     * 循环2
     */
    public static class Solution3 {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            Stack<TreeNode> stack1 = new Stack<>();
            stack1.push(p);
            Stack<TreeNode> stack2 = new Stack<>();
            stack2.push(q);
            while (!stack1.isEmpty()) {
                p = stack1.pop();
                q = stack2.pop();
                while (p != null) {
                    if (q == null) {
                        return false;
                    }
                    if (p.val != q.val) {
                        return false;
                    }
                    stack1.push(p.right);
                    stack2.push(q.right);
                    p = p.left;
                    q = q.left;
                }
                if (q != null) {
                    return false;
                }
            }
            return q == null;
        }
    }
}
