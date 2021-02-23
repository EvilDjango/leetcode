package com.deerhunter;

/**
 * 209. Minimum Size Subarray Sum
 * Medium
 * <p>
 * 3395
 * <p>
 * 135
 * <p>
 * Add to List
 * <p>
 * Share
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * Example 2:
 * <p>
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * Example 3:
 * <p>
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * <p>
 * <p>
 * Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/2/19 17:22
 */
public class Topic209 {
    /**
     * 暴力算法
     */
    public static class Solution1 {
        public int minSubArrayLen(int target, int[] nums) {
            int n = nums.length;
            int minLen = 0;
            for (int i = 0; i < n; i++) {
                int[] sum = new int[n];
                for (int j = i; j < n; j++) {
                    if (j == i) {
                        sum[j] = nums[j];
                    } else {
                        sum[j] = sum[j - 1] + nums[j];
                    }
                    if (sum[j] >= target) {
                        minLen = minLen == 0 ? j - i + 1 : Math.min(minLen, j - i + 1);
                        break;
                    }
                }
            }
            return minLen;
        }
    }

    /**
     * 二分查找
     */
    public static class Solution2 {
        public int minSubArrayLen(int target, int[] nums) {
            int n = nums.length;
            if (n == 0) {
                return 0;
            }
            int[] sums = new int[n];
            sums[0] = nums[0];
            for (int i = 1; i < n; i++) {
                sums[i] = sums[i - 1] + nums[i];
            }
            int minLen = 0;
            for (int i = 0; i < n; i++) {
                int len = binarySearch(sums, sums[i] - nums[i], target, i, n - 1);
                if (len > 0) {
                    minLen = minLen == 0 ? len : Math.min(minLen, len);
                }
            }
            return minLen;
        }

        private int binarySearch(int[] sums, int loss, int target, int l, int r) {
            int left = l;
            int right = r;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                int sum = sums[mid] - loss;
                if (sum >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return sums[right] - loss >= target ? right - l + 1 : 0;
        }
    }

    /**
     * 双指针法
     */
    public static class Solution3 {
        public int minSubArrayLen(int target, int[] nums) {
            int n = nums.length;
            int left = 0;
            int sum = 0;
            int minLen = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                sum += nums[i];
                while (sum >= target) {
                    minLen = Math.min(minLen, i - left + 1);
                    sum -= nums[left++];
                }
            }
            return minLen == Integer.MAX_VALUE ? 0 : minLen;
        }
    }
}
