package com.deerhunter;

import com.deerhunter.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 通过次数265,708提交次数549,322
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 6/30/21 4:04 PM
 */
public class Topic234 {
    /**
     * 傻瓜算法
     */
    public static class Solution1 {
        public boolean isPalindrome(ListNode head) {
            List<Integer> list = new ArrayList<>();
            while (head != null) {
                list.add(head.val);
                head = head.next;
            }
            int l = 0, r = list.size() - 1;
            while (l < r) {
                if (!list.get(l).equals(list.get(r))) {
                    return false;
                }
                l++;
                r--;
            }
            return true;
        }
    }

    /**
     * 利用快慢指针找到链表中点，同时把左半边链表翻转，然后判断是否是回文
     */
    public static class Solution2 {
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) {
                return true;
            }
            ListNode slow = head;
            ListNode fast = head.next;
            ListNode next = slow.next;
            slow.next = null;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                ListNode prev = slow;
                slow = next;
                next = next.next;
                slow.next = prev;
            }
            boolean odd = fast == null;

            ListNode l = slow;
            ListNode r = next;

            if (odd) {
                ListNode temp = l;
                l = l.next;
                temp.next = next;
                next = temp;
            }

            boolean isPalindrome = true;
            while (l != null) {
                if (l.val != r.val) {
                    isPalindrome = false;
                }
                r = r.next;
                ListNode temp = l;
                l = l.next;
                temp.next = next;
                next = temp;
            }

            return isPalindrome;
        }
    }

    /**
     * 本质上和Solution1一样，但是更清晰
     */
    public static class Solution3 {
        public boolean isPalindrome(ListNode head) {
            if (head == null) {
                return true;
            }
            ListNode mid = findMid(head);
            ListNode newHead = reverse(mid.next);
            boolean isPalindrome = true;
            ListNode r = newHead;
            while (r != null) {
                if (r.val != head.val) {
                    isPalindrome = false;
                    break;
                }
                r = r.next;
                head = head.next;
            }
            reverse(newHead);
            return isPalindrome;
        }

        /**
         * @param head
         * @return
         */
        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            while (head != null) {
                ListNode next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            return prev;
        }

        private ListNode findMid(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode slow = head, fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }
}
