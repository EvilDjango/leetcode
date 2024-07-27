package com.deerhunter.topic;

import com.deerhunter.common.ListNode;
import com.deerhunter.common.Utils;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-01
 */
public class Topic023 {
    /**
     * 参考官方题解中的python版分治解法
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int len = lists.length;
        int step = 1;
        while (step < len) {
            for (int i = 0; i + step < len; i += 2 * step) {
                lists[i] = Utils.merge(lists[i], lists[i + step]);
            }
            step *= 2;
        }
        return lists[0];
    }

    /**
     * 参考官方题解写的解法
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists2(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length + 1, Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while ((!queue.isEmpty())) {
            ListNode node = queue.remove();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                queue.add(node.next);
            }
        }

        return dummy.next;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);


        ListNode cur = dummy;
        int len = lists.length;
        while (true) {
            int index = -1;
            ListNode minNode = null;
            for (int i = 0; i < len; i++) {
                ListNode node = lists[i];
                if (node == null) {
                    lists[i] = lists[len - 1];
                    len--;
                    i--;
                    continue;
                }
                if (minNode == null || node.val < minNode.val) {
                    minNode = node;
                    index = i;
                }
            }
            if (minNode == null) {
                break;
            }

            lists[index] = minNode.next;
            cur.next = minNode;
            cur = cur.next;
        }
        return dummy.next;
    }

}
