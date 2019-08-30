package com.deerhunter;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-30
 */
public class Solution016 {
    /**
     * 参考15题优秀解法
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int len;
        if (nums == null || (len = nums.length) < 3) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            // 去重
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int l = i + 1;
            int r = len - 1;
            while (l < r) {
                // 去重
                if (l - 1 > i && nums[l] == nums[l - 1]) {
                    l++;
                    continue;
                }
                // 去重
                if (r + 1 < len && nums[r] == nums[r + 1]) {
                    r--;
                    continue;
                }
                int tempSum = nums[i] + nums[l] + nums[r];
                int diff = tempSum - target;
                int absDiff = Math.abs(diff);
                if (diff == 0) {
                    return tempSum;
                }
                if (absDiff < minDiff) {
                    minDiff = absDiff;
                    sum = tempSum;
                }
                if (diff < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return sum;
    }

    /**
     * 暴力算法
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest1(int[] nums, int target) {
        int minDiff = Integer.MAX_VALUE;
        int sum = 0;

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    int tempSum = nums[i] + nums[j] + nums[k];
                    int diff = Math.abs(tempSum - target);
                    if (0 == diff) {
                        return tempSum;
                    }
                    if (diff < minDiff) {
                        minDiff = diff;
                        sum = tempSum;
                    }
                }
            }
        }
        return sum;
    }
}