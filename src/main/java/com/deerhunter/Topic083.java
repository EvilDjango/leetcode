package com.deerhunter;

import com.deerhunter.common.ListNode;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * <p>
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-04-01
 */
public class Topic083 {
    /**
     * 跳过重复元素
     */
    public static class Solution1 {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode cur = head;
            while (cur != null && cur.next != null) {
                // 跳过重复的元素
                if (cur.val == cur.next.val) {
                    cur = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            return head;
        }
    }

    /**
     * 创建一个新的链表，每当节点符合条件时才加入
     */
    public static class Solution2 {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummy = new ListNode(0);
            ListNode newCur = dummy;
            ListNode cur = head;
            while (cur != null) {
                // 当且仅当当前节点和后驱节点值不同时，将其加入新的链表
                if (cur.next == null || cur.next.val != cur.val) {
                    newCur.next = cur;
                    newCur = cur;
                }
                cur = cur.next;
            }
            return dummy.next;
        }
    }

}
