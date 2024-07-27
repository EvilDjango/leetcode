package com.deerhunter.topic;

/**
 * 162. 寻找峰值
 * 峰值元素是指其值大于左右相邻值的元素。
 * <p>
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * <p>
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 * <p>
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。
 * 说明:
 * <p>
 * 你的解法应该是 O(logN) 时间复杂度的。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/11/20 15:38
 */
public class Topic162 {
    /**
     * 二分查找，递归
     */
    public static class Solution1 {
        public int findPeakElement(int[] nums) {
            return findPeakElement(nums, 0, nums.length - 1);
        }

        private int findPeakElement(int[] nums, int left, int right) {
            if (left == right) {
                return left;
            }
            int mid = (right - left) / 2 + left;
            if (nums[mid + 1] > nums[mid]) {
                return findPeakElement(nums, mid + 1, right);
            } else {
                return findPeakElement(nums, left, mid);
            }
        }
    }

    /**
     * 二分查找，迭代
     */
    public static class Solution2 {
        public int findPeakElement(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (nums[mid + 1] > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return right;
        }
    }
}

