package com.deerhunter;

import com.deerhunter.common.ListNode;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 * 通过次数44,311提交次数89,210
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/4/14 15:17
 */
public class Topic092 {
    public static class Solution1 {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            if (m > n) {
                return head;
            }

            ListNode dummy = new ListNode(0);
            dummy.next = head;
            head = dummy;
            // i表示当前元素的下标
            int i = -1;
            // 当head为翻转前一个元素时，终止循环
            while (i + 2 < m) {
                i++;
                head = head.next;
            }

            // 断开点
            ListNode pivot = head;
            head = head.next;
            // 翻转链表头部
            ListNode reverseHead = head;
            // 翻转链表尾部
            ListNode reverseTail = head;
            i++;

            ListNode next = head.next;
            while (i + 1 <= n) {
                head.next = reverseHead;
                reverseHead = head;
                head = next;
                if (next != null) {
                    next = next.next;
                }
                i++;
            }
            // 将翻转链表拼接到原链表中
            pivot.next = reverseHead;
            reverseTail.next = head;
            return dummy.next;
        }
    }

    /**
     * 递归解法
     */
    public static class Solution2 {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            head = dummy;
            // i表示下一个元素的索引
            int i = 0;
            while (i + 1 < m) {
                head = head.next;
                i++;
            }
            // heads[0]是倒转链表头，heads[1]是最后一段链表头
            ListNode[] heads = new ListNode[2];
            ListNode reverseTail = reverse(head.next, n, i, heads);
            reverseTail.next = heads[1];
            head.next = heads[0];
            return dummy.next;
        }

        private ListNode reverse(ListNode cur, int n, int i, ListNode[] lastPart) {
            if (i + 1 == n) {
                lastPart[0] = cur;
                lastPart[1] = cur.next;
                return cur;
            }
            ListNode head = reverse(cur.next, n, i + 1, lastPart);
            head.next = cur;
            return cur;
        }
    }

    /**
     * 递归法。参考官方题解
     */
    public static class Solution3 {
        private boolean stop = false;
        private ListNode left = null;

        public ListNode reverseBetween(ListNode head, int m, int n) {
            this.stop = false;
            this.left = head;
            recurseAndReverse(head, m, n);
            return head;
        }

        private void recurseAndReverse(ListNode right, int m, int n) {
            if (n == 1) {
                return;
            }
            if (m > 1) {
                this.left = this.left.next;
            }
            right = right.next;
            recurseAndReverse(right, m - 1, n - 1);

            if (this.stop) {
                return;
            }

            if (right == this.left || right.next == this.left) {
                this.stop = true;
            }
            if (!this.stop) {
                int temp = this.left.val;
                this.left.val = right.val;
                right.val = temp;
                this.left = this.left.next;
            }
        }
    }

    /**
     * 迭代法。参考官方题解。思路和解法一一致，但是写法更优雅一些。
     */
    public static class Solution4 {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            ListNode prev = null;
            ListNode cur = head;
            while (m > 1) {
                prev = cur;
                cur = cur.next;
                m--;
                n--;
            }
            // 链表前部（无需反转的部分）的尾结点
            ListNode conjunction = prev;
            // 反转链表的尾结点
            ListNode tail = cur;
            // 保存cur的next指针
            ListNode next;
            while (n >= 1) {
                next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
                n--;
            }
            // 循环结束时，prev是反转链表的头，cur是链表后部（无需反转的部分）的头结点

            if (conjunction == null) {
                head = prev;
            } else {
                conjunction.next = prev;
            }

            tail.next = cur;

            return head;
        }
    }

    /**
     * 迭代解法，参考评论中的优秀解法
     */
    public static class Solution5 {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode prev = dummy;
            while (m > 1) {
                prev = prev.next;
                m--;
                n--;
            }
            head = prev.next;
            while (n > 1) {
                ListNode next = head.next;
                head.next = head.next.next;
                next.next = prev.next;
                prev.next = next;
                n--;
            }
            return dummy.next;
        }
    }
}
