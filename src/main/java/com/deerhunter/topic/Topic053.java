package com.deerhunter.topic;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-08
 */
public class Topic053 {
    // 暴力解法，会超时
    public static class Solution1 {
        public int maxSubArray(int[] nums) {
            // dp[i][j]表示从子序列【i,j]的长度
            int len = nums.length;
            if (len == 0) {
                return 0;
            }
            int max = Integer.MIN_VALUE;
            int[][] dp = new int[len][len];
            for (int i = 0; i < len; i++) {
                int val = nums[i];
                dp[i][i] = val;
                max = Math.max(max, val);
                for (int j = 0; j < i; j++) {
                    dp[j][i] = dp[j][i - 1] + val;
                    max = Math.max(dp[j][i], max);
                }
            }
            return max;
        }
    }

    // 连续累加，若当前的和已经小于0了，则弃掉前面的序列，另起一个序列。
    public static class Solution2 {
        public int maxSubArray(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int sum = 0, max = nums[0];
            for (int num : nums) {
                sum += num;
                max = Math.max(max, sum);
                if (sum < 0) {
                    sum = 0;
                }
            }

            return max;
        }
    }

    // 分治法： 选一个中点P，那么最佳自序列的情况有三种：落在P左边，落在P右边，包含P。分情况讨论，无限分割，直至答案明显可求。
    public static class Solution3 {
        public int maxSubArray(int[] nums) {
            return maxSubArray(nums, 0, nums.length);
        }

        private int maxSubArray(int[] nums, int left, int right) {
            if (left + 1 == right) {
                return nums[left];
            }
            int pivot = (right - left) / 2 + left;
            int leftMax = maxSubArray(nums, left, pivot);
            int rightMax = maxSubArray(nums, pivot, right);
            int crossSum = maxCrossSum(nums, left, pivot, right);
            return Math.max(crossSum, Math.max(leftMax, rightMax));
        }

        private int maxCrossSum(int[] nums, int left, int pivot, int right) {
            int leftSum = 0;
            int leftMax = Integer.MIN_VALUE;
            for (int i = pivot - 1; i >= left; i--) {
                leftSum += nums[i];
                leftMax = Math.max(leftMax, leftSum);
            }
            int rightSum = 0;
            int rightMax = Integer.MIN_VALUE;
            for (int i = pivot; i < right; i++) {
                rightSum += nums[i];
                rightMax = Math.max(rightMax, rightSum);
            }
            return leftMax + rightMax;
        }
    }

    // 动态规划,原地修改nums作为dp数组，dp数组记录截止当前位置的最大和
    public static class Solution4 {
        public int maxSubArray(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                nums[i] = Math.max(nums[i], nums[i - 1] + nums[i]);
                max = Math.max(max, nums[i]);
            }

            return max;
        }
    }
}
