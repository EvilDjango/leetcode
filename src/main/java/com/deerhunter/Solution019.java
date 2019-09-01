package com.deerhunter;

import com.deerhunter.common.ListNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-31
 */
public class Solution019 {
    /**
     * 根据官方题解自己写的解法
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        Queue<ListNode> queue = new ArrayDeque<>(n + 1);
        ListNode cur = head;
        do {
            queue.add(cur);
            if (queue.size() > (n + 1)) {
                queue.remove();
            }
            cur = cur.next;
        } while (cur != null);

        // 倒数第n个节点是首节点
        if (queue.size() == n) {
            return head.next;
        }
        ListNode prev = queue.remove();
        prev.next = queue.remove().next;
        return head;
    }


}

