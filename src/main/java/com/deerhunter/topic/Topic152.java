package com.deerhunter.topic;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/11/13 12:05
 */
public class Topic152 {
    public static class Solution1 {
        public int maxProduct(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return nums[0];
            }
            int max = nums[0];
            int curMax = nums[0];
            int curMin = nums[0];
            for (int i = 1; i < n; i++) {
                curMax *= nums[i];
                curMin *= nums[i];
                int temp = Math.max(nums[i], Math.max(curMax, curMin));
                curMin = Math.min(nums[i], Math.min(curMax, curMin));
                curMax = temp;
                max = Math.max(max, curMax);
            }
            return max;
        }
    }
}
