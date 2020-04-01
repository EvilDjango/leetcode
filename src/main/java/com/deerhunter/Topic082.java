package com.deerhunter;

import com.deerhunter.common.ListNode;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 * <p>
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-04-01
 */
public class Topic082 {
    public static class Solution1 {
        public ListNode deleteDuplicates(ListNode head) {
            // 哑节点
            ListNode dumbHead = new ListNode(0);
            dumbHead.next = head;
            ListNode newHead = dumbHead;
            ListNode prev = null;
            ListNode cur = head;
            while (cur != null) {
                // 当且仅当当前节点的值与前驱节点和后驱节点都不同时，应当保留当前节点
                if ((prev == null || cur.val != prev.val) && (cur.next == null || cur.val != cur.next.val)) {
                    newHead.next = cur;
                    newHead = cur;
                }
                prev = cur;
                cur = cur.next;
            }
            newHead.next = null;
            return dumbHead.next;
        }
    }

    public static class Solution2 {
        public ListNode deleteDuplicates(ListNode head) {
            // 哑节点
            ListNode dumbHead = new ListNode(0);
            dumbHead.next = head;
            ListNode newHead = dumbHead;
            ListNode cur = head;
            while (cur != null) {
                // 直接跳过重复节点
                if (cur.next != null && cur.next.val == cur.val) {
                    while (cur.next != null && cur.next.val == cur.val) {
                        cur = cur.next;
                    }
                } else {
                    // 非重复节点，加入到新的链表中
                    newHead.next = cur;
                    newHead = cur;
                }
                cur = cur.next;
            }
            newHead.next = null;
            return dumbHead.next;
        }
    }
}
