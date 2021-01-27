package com.deerhunter;

import com.deerhunter.common.ListNode;

/**
 * 203. Remove Linked List Elements
 * Easy
 * <p>
 * 2355
 * <p>
 * 114
 * <p>
 * Add to List
 * <p>
 * Share
 * Remove all elements from a linked list of integers that have value val.
 * <p>
 * Example:
 * <p>
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/1/27 11:02
 */
public class Topic203 {
    public static class Solution1 {
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            head = dummy;
            while (head.next != null) {
                if (head.next.val == val) {
                    head.next = head.next.next;
                } else {
                    head = head.next;
                }
            }
            return dummy.next;
        }
    }
}
