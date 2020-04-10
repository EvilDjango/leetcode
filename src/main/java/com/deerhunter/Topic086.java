package com.deerhunter;

import com.deerhunter.common.ListNode;

import java.util.function.ObjIntConsumer;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * <p>
 * 你应当保留两个分区中每个节点的初始相对位置。
 * <p>
 * 示例:
 * <p>
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 * 通过次数32,213提交次数56,644
 * 在真实的面试中遇到过这道题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020/4/8
 */
public class Topic086 {
    /**
     * 生成两个链表：小于目标值的链表，大于等于目标值的链表，最后将两个表连接起来
     */
    public static class Solution1 {
        public ListNode partition(ListNode head, int x) {
            ListNode bigHead = new ListNode(0);
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode cur = dummy;
            ListNode big = bigHead;
            while (cur.next != null) {
                if (cur.next.val >= x) {
                    big.next = cur.next;
                    big = cur.next;
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            big.next = null;
            cur.next = bigHead.next;
            return dummy.next;
        }
    }

    /**
     * 原地操作，通过链表的剪接来达到目的
     * 这个写法像狗屎一样，优雅的写法参见解法三
     */
    public static class Solution2 {
        public ListNode partition(ListNode head, int x) {
            ListNode dummy = new ListNode(0);
            ListNode lastSmall = null;
            dummy.next = head;
            ListNode cur = dummy;
            while (cur.next != null) {
                if (cur.next.val < x) {
                    if (lastSmall == null) {
                        if (cur.next == head) {
                            lastSmall = head;
                            cur = cur.next;
                        } else {
                            lastSmall = cur.next;
                            cur.next = cur.next.next;
                            lastSmall.next = head;
                        }
                        dummy.next = lastSmall;
                    } else {
                        if (lastSmall.next == cur.next) {
                            lastSmall = lastSmall.next;
                            cur = cur.next;
                        } else {
                            ListNode temp = lastSmall.next;
                            lastSmall.next = cur.next;
                            lastSmall = cur.next;
                            cur.next = cur.next.next;
                            lastSmall.next = temp;
                        }
                    }
                } else {
                    cur = cur.next;
                }
            }
            return dummy.next;
        }
    }


    /**
     * 解法二的简化版
     */
    public static class Solution3 {
        public ListNode partition(ListNode head, int x) {
            ListNode dummy = new ListNode(0);
            ListNode lastSmall = dummy;
            dummy.next = head;
            ListNode cur = dummy;
            while (cur.next != null) {
                if (cur.next.val < x) {
                    if (lastSmall.next == cur.next) {
                        cur = cur.next;
                        lastSmall = lastSmall.next;
                    } else {
                        ListNode temp = lastSmall.next;
                        lastSmall.next = cur.next;
                        lastSmall = cur.next;
                        cur.next = cur.next.next;
                        lastSmall.next = temp;
                    }
                } else {
                    cur = cur.next;
                }
            }
            return dummy.next;
        }
    }

    /**
     * 参考官方题解
     */
    public static class Solution4 {
        public ListNode partition(ListNode head, int x) {
            ListNode bigHead = new ListNode(0);
            ListNode smallHead = new ListNode(0);
            ListNode big = bigHead;
            ListNode small = smallHead;
            while (head != null) {
                if (head.val >= x) {
                    big.next = head;
                    big = big.next;
                } else {
                    small.next = head;
                    small = small.next;
                }
                head = head.next;
            }
            big.next = null;
            small.next = bigHead.next;
            return smallHead.next;
        }
    }
}
