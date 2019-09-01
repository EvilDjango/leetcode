package com.deerhunter.common;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-01
 */
public class Utils {
    private Utils() {
    }

    public static ListNode createLinkList(int[] nums) {
        if (null == nums || nums.length == 0) {
            return null;
        }
        ListNode head = new ListNode(nums[0]);
        ListNode cur = head;
        for (int i = 1; i < nums.length; i++) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }
        return head;
    }

    /**
     * 二分查找第一个等于target的元素下标
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch(int[] nums, int target) {
        int lowerBound = lowerBound(nums, target);
        if (lowerBound < nums.length && nums[lowerBound] == target) {
            return lowerBound;
        }
        return -1;
    }

    /**
     * 查找给定的数组中第一个不小于target的元素下标
     *
     * @param nums
     * @param target
     * @return
     */
    public static int lowerBound(int[] nums, int target) {
        if (null == nums) {
            throw new NullPointerException();
        }
        int left = 0;
        int right = nums.length;
        while (left < right) {
            // 防止溢出
            int mid = left + (right - left) / 2;
            if (target <= nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 查找给定的数组中第一个大于target的元素下标
     *
     * @param nums
     * @param target
     * @return
     */
    public static int upperBound(int[] nums, int target) {
        if (null == nums) {
            throw new NullPointerException();
        }
        int left = 0;
        int right = nums.length;
        while (left < right) {
            // 防止溢出
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}