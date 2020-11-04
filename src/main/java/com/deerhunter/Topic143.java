package com.deerhunter;

import com.deerhunter.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * <p>
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * <p>
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/10/29 21:29
 */
public class Topic143 {

    public static class Solution1 {
        public void reorderList(ListNode head) {
            if (head == null) {
                return;
            }
            List<ListNode> nodes = new ArrayList<>();
            ListNode cur = head;
            while (cur != null) {
                nodes.add(cur);
                cur = cur.next;
            }
            int n = nodes.size();
            if (n == 1) {
                return;
            }
            for (int i = 0; i <= (n - 1) / 2; i++) {
                nodes.get(i).next = nodes.get(n - i - 1);
                nodes.get(n - i - 1).next = nodes.get(i + 1);
            }
            nodes.get(n / 2).next = null;
        }
    }

    public static class Solution2 {
        public void reorderList(ListNode head) {
            if (head == null) {
                return;
            }
            ListNode slow = head;
            ListNode fast;
            while (slow != null && slow.next != null) {
                fast = slow;
                ListNode pre = null;
                while (fast.next != null) {
                    pre = fast;
                    fast = fast.next;
                }
                if (pre != null) {
                    pre.next = null;
                }
                fast.next = slow.next;
                slow.next = fast;
                slow = fast.next;
            }
        }
    }

    /**
     * 寻找链表中点 + 链表逆序 + 合并链表
     */
    public static class Solution3 {
        public void reorderList(ListNode head) {
            if (head == null || head.next == null) {
                return;
            }
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode leftTail = slow;
            slow = slow.next;
            leftTail.next = null;
            ListNode right = null;
            while (slow != null) {
                ListNode next = slow.next;
                slow.next = right;
                right = slow;
                slow = next;
            }

            while (right != null) {
                ListNode next = head.next;
                head.next = right;
                ListNode nextRight = right.next;
                right.next = next;
                head = next;
                right = nextRight;
            }
        }
    }
}
