package com.deerhunter.topic;

import com.deerhunter.common.ListNode;

/**
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 进阶：
 * <p>
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 * <p>
 * 输入：head = []
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 * 通过次数100,892提交次数150,349
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/11/6 09:54
 */
public class Topic148 {
    /**
     * 希尔排序,失败
     */
    public static class Solution1 {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            int n = 0;
            ListNode cur = head;
            while (cur != null) {
                n++;
                cur = cur.next;
            }
            ListNode dummy = new ListNode(Integer.MIN_VALUE);
            dummy.next = head;
            for (int step = n / 2; step > 0; step /= 2) {
                ListNode prev = dummy.next;
                cur = forward(dummy.next, step)[0];
                for (int i = step; cur != null; i += step) {
                    if (cur.val < prev.val) {
                        ListNode cur2 = dummy.next;
                        ListNode prev2 = dummy;
                        ListNode adjacent = dummy;
                        for (int j = 0; j < i && cur2.val <= cur.val; j += step) {
                            prev2 = cur2;
                            ListNode[] nodes = forward(cur2, step);
                            cur2 = nodes[0];
                            adjacent = nodes[1];
                        }
                        adjacent.next = cur2.next;
                        cur2.next = prev2.next;
                        prev2.next = cur2;
                    }
                    prev = cur;
                    cur = forward(cur, step)[0];
                }
            }
            return dummy.next;
        }

        private ListNode[] forward(ListNode node, int step) {
            ListNode adjacent = null;
            for (int i = 0; i < step; i++) {
                if (node == null) {
                    break;
                }
                adjacent = node;
                node = node.next;
            }
            return new ListNode[]{node, adjacent};
        }
    }

    /**
     * 归并排序,递归
     */
    public static class Solution2 {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode[] heads = splitList(head);
            ListNode sortedLeft = sortList(heads[0]);
            ListNode sortedRight = sortList(heads[1]);
            return mergeSortedList(sortedLeft, sortedRight);
        }

        private static ListNode mergeSortedList(ListNode sortedLeft, ListNode sortedRight) {
            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;
            while (sortedLeft != null && sortedRight != null) {
                if (sortedLeft.val <= sortedRight.val) {
                    cur.next = sortedLeft;
                    sortedLeft = sortedLeft.next;
                } else {
                    cur.next = sortedRight;
                    sortedRight = sortedRight.next;
                }
                cur = cur.next;
            }
            if (sortedLeft != null) {
                cur.next = sortedLeft;
            }
            if (sortedRight != null) {
                cur.next = sortedRight;
            }
            return dummy.next;
        }

        private static ListNode[] splitList(ListNode head) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode fast = dummy;
            ListNode slow = dummy;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            ListNode rightHead = slow.next;
            slow.next = null;
            return new ListNode[]{head, rightHead};
        }
    }

    /**
     * 归并排序,循环
     */
    public static class Solution3 {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode dummy = new ListNode(0);
            dummy.next = head;
            int radius = 1;
            int parts = 2;
            while (parts > 1) {
                ListNode cur = dummy.next;
                ListNode prev = dummy;
                parts = 0;
                while (cur != null) {
                    parts++;
                    ListNode right = forward(cur, radius);
                    if (right == null) {
                        break;
                    }
                    ListNode rightBound = forward(right, radius);
                    prev = mergeList(prev, cur, right, rightBound);
                    cur = rightBound;
                }
                radius *= 2;
            }
            return dummy.next;
        }

        private ListNode mergeList(ListNode prev, ListNode left, ListNode right, ListNode rightBound) {
            ListNode leftBound = right;
            while (left != leftBound && right != rightBound) {
                if (left.val <= right.val) {
                    prev.next = left;
                    left = left.next;
                } else {
                    prev.next = right;
                    right = right.next;
                }
                prev = prev.next;
            }
            if (left != leftBound) {
                prev.next = left;

            }
            if (right != rightBound) {
                prev.next = right;
            }
            while (prev.next != rightBound) {
                prev = prev.next;
            }
            prev.next = rightBound;
            return prev;
        }

        public ListNode forward(ListNode node, int size) {
            for (int i = 0; i < size; i++) {
                if (node == null) {
                    return null;
                }
                node = node.next;
            }
            return node;
        }
    }
}
