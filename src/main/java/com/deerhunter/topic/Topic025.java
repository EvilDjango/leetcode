package com.deerhunter.topic;

import com.deerhunter.common.ListNode;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例 :
 * <p>
 * 给定这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明 :
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-03
 */
public class Topic025 {
    /**
     * 递归解法
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup2(ListNode head, int k) {
        int i = -1;
        ListNode cur = head;
        ListNode[] stack = new ListNode[k];
        while (i < k - 1) {
            if (cur == null) {
                return head;
            }
            stack[++i] = cur;
            cur = cur.next;
        }
        while (i > 0) {
            stack[i].next = stack[i - 1];
            i--;
        }
        stack[0].next = reverseKGroup2(cur, k);

        return stack[k - 1];
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode[] stack = new ListNode[k];
        int i = -1;
        ListNode dummy = new ListNode(0);
        ListNode last = dummy;
        ListNode cur = head;
        while (cur != null) {
            stack[++i] = cur;
            cur = cur.next;
            if (i == stack.length - 1) {
                stack[0].next = stack[i].next;
                while (i >= 0) {
                    last.next = stack[i--];
                    last = last.next;
                }
                i = -1;
            } else if (cur == null) {
                last.next = stack[0];
            }
        }
        return dummy.next;
    }
}
