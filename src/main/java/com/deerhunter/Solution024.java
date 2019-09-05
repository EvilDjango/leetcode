package com.deerhunter;

import com.deerhunter.common.ListNode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-03
 */
public class Solution024 {
    /**
     * 递归解法
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs2(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }
        ListNode second = head.next;
        head.next = swapPairs2(second.next);
        second.next = head;
        return second;
    }

    public static ListNode swapPairs(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        cur.next = head;
        ListNode first;
        ListNode second;
        while ((first = cur.next) != null && (second = cur.next.next) != null) {
            cur.next = second;
            first.next = second.next;
            second.next = first;
            cur = first;
        }
        return dummy.next;
    }
}