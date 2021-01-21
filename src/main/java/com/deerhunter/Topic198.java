package com.deerhunter;

/**
 * 198. House Robber
 * Medium
 * <p>
 * 6359
 * <p>
 * 183
 * <p>
 * Add to List
 * <p>
 * Share
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 * <p>
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/1/20 10:33
 */
public class Topic198 {
    public static class Solution {
        public int rob(int[] nums) {
            int n = nums.length;
            int[] dp = new int[2];
            for (int i = 0; i < n; i++) {
                int temp = dp[1];
                dp[1] = dp[0] + nums[i];
                dp[0] = Math.max(temp, dp[0]);
            }
            return Math.max(dp[0], dp[1]);
        }
    }
}
