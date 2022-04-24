package com.deerhunter;

/**
 * 剑指 Offer 57. 和为s的两个数字
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * 示例 2：
 * <p>
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 通过次数190,826提交次数281,945
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/24 下午10:39
 */
public class Offer057A {
    public int[] twoSum(int[] nums, int target) {
        for (int l = 0, r = nums.length; l < r; ) {
            int sum = nums[l] + nums[r];
            if (sum < target) {
                l++;
            } else if (sum > target) {
                r--;
            } else {
                return new int[]{nums[l], nums[r]};
            }
        }
        return null;
    }
}
