package com.deerhunter;

import com.deerhunter.common.Node;

/**
 * 给定一个二叉树
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *  
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 *  
 * <p>
 * 提示：
 * <p>
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/15 13:38
 */
public class Topic117 {
    /**
     * 迭代
     */
    public static class Solution1 {
        public static Node connect(Node root) {
            if (root == null) {
                return null;
            }
            Node dumb = new Node(0);
            Node cur = root;
            Node prev;
            while (true) {
                prev = dumb;
                while (cur != null) {
                    if (cur.left != null) {
                        prev.next = cur.left;
                        prev = prev.next;
                    }
                    if (cur.right != null) {
                        prev.next = cur.right;
                        prev = prev.next;
                    }
                    cur = cur.next;
                }
                if (prev == dumb) {
                    break;
                }
                cur = dumb.next;
            }
            return root;
        }

    }
}
