package com.deerhunter;

/**
 * 154. 寻找旋转排序数组中的最小值 II
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 请找出其中最小的元素。
 * <p>
 * 注意数组中可能存在重复的元素。
 * <p>
 * 示例 1：
 * <p>
 * 输入: [1,3,5]
 * 输出: 1
 * 示例 2：
 * <p>
 * 输入: [2,2,2,0,1]
 * 输出: 0
 * 说明：
 * <p>
 * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 * 通过次数40,996提交次数81,244
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/11/13 16:29
 */
public class Topic154 {
    public static class Solution1 {
        public int findMin(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while (right > 0 && nums[right] == nums[0]) {
                right--;
            }
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (nums[mid] <= nums[right]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return nums[right];
        }
    }

    /**
     * 参考官方题解
     */
    public static class Solution2 {
        public int findMin(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int pivot = (right - left) / 2 + left;
                if (nums[pivot] < nums[right]) {
                    right = pivot;
                } else if (nums[pivot] > nums[right]) {
                    left = pivot + 1;
                } else {
                    right -= 1;
                }
            }
            return nums[left];
        }
    }
}
