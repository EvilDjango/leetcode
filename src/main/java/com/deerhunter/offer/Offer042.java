package com.deerhunter.offer;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * <p>
 * 要求时间复杂度为O(n)。
 * <p>
 * <p>
 * <p>
 * 示例1:
 * <p>
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 * 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/
 * <p>
 * <p>
 * <p>
 * 通过次数300,024提交次数494,243
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/2 上午10:49
 */
public class Offer042 {
    /**
     * 动态规划
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int curMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curMax = Math.max(curMax + nums[i], nums[i]);
            max = Math.max(max, curMax);
        }
        return max;
    }

    /**
     * 分治
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        return getInfo(nums, 0, nums.length).mSum;
    }

    private Status getInfo(int[] nums, int left, int right) {
        if (left + 1 == right) {
            return new Status(nums[left], nums[left], nums[left], nums[left]);
        }
        int mid = left + ((right - left) >> 1);
        Status lStatus = getInfo(nums, left, mid);
        Status rStatus = getInfo(nums, mid, right);
        int lSum = Math.max(lStatus.lSum, lStatus.iSum + rStatus.lSum);
        int rSum = Math.max(rStatus.rSum, rStatus.iSum + lStatus.rSum);
        int iSum = lStatus.iSum + rStatus.iSum;
        int mSum = Math.max(Math.max(lStatus.mSum, rStatus.mSum),  rStatus.lSum + lStatus.rSum);
        return new Status(lSum,rSum,iSum,mSum);
    }

    private class Status {
        // 从左端点起的最大子序列和，在右端点结束的最大子序列和，区间和，区间最大子序列和
        int lSum, rSum, iSum, mSum;

        public Status(int lSum, int rSum, int iSum, int mSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.iSum = iSum;
            this.mSum = mSum;
        }
    }
}
