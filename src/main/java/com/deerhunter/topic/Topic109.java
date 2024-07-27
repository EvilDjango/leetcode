package com.deerhunter.topic;

import com.deerhunter.common.ListNode;
import com.deerhunter.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * <p>
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/12 22:22
 */
public class Topic109 {
    /**
     * 傻瓜式解法
     */
    public static class Solution1 {
        public TreeNode sortedListToBST(ListNode head) {
            List<Integer> list = new ArrayList<>();
            while (head != null) {
                list.add(head.val);
                head = head.next;
            }
            return buildBst(list, 0, list.size());
        }

        private TreeNode buildBst(List<Integer> list, int left, int right) {
            if (left == right) {
                return null;
            }
            int middle = left + (right - left - 1) / 2;
            TreeNode root = new TreeNode(list.get(middle));
            root.left = buildBst(list, left, middle);
            root.right = buildBst(list, middle + 1, right);
            return root;
        }
    }

    /**
     * 直接读取链表
     */
    public static class Solution2 {
        public TreeNode sortedListToBST(ListNode head) {
            int length = 0;
            ListNode cur = head;
            while (cur != null) {
                length++;
                cur = cur.next;
            }
            TreeNode treeNode = buildBst(head, length);
            return treeNode;
        }

        private TreeNode buildBst(ListNode head, int length) {
            if (length == 0) {
                return null;
            }
            ListNode rightHead = head;
            int index = 0;
            int middleIndex = (length - 1) / 2;
            int middle;
            while (index < middleIndex) {
                index++;
                rightHead = rightHead.next;
            }
            middle = rightHead.val;
            rightHead = rightHead.next;
            TreeNode root = new TreeNode(middle);
            root.left = buildBst(head, middleIndex);
            root.right = buildBst(rightHead, length - middleIndex - 1);
            return root;
        }

    }

    /**
     * 双指针法，参考题解
     */
    public static class Solution3 {
        public TreeNode sortedListToBST(ListNode head) {
            return buildBst(head, null);
        }

        private TreeNode buildBst(ListNode left, ListNode right) {
            if (left == right) {
                return null;
            }
            ListNode slow = left, fast = left;
            while (fast != right && fast.next != right) {
                slow = slow.next;
                fast = fast.next.next;
            }
            TreeNode root = new TreeNode(slow.val);
            root.left = buildBst(left, slow);
            root.right = buildBst(slow.next, right);
            return root;
        }
    }

    /**
     * 分治+ 中序遍历优化，参考题解
     */
    public static class Solution4 {
        private ListNode globalHead;

        public TreeNode sortedListToBST(ListNode head) {
            int length = 0;
            ListNode cur = head;
            while (cur != null) {
                length++;
                cur = cur.next;
            }
            globalHead = head;
            return buildBst(length);
        }

        private TreeNode buildBst(int length) {
            if (length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(0);
            int leftLength = (length - 1) / 2;
            root.left = buildBst(leftLength);
            root.val = globalHead.val;
            globalHead = globalHead.next;
            root.right = buildBst(length - 1 - leftLength);
            return root;
        }
    }
}
