package com.deerhunter.topic;

import java.util.Arrays;

/**
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 * <p>
 * 如果数组元素个数小于 2，则返回 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * 示例 2:
 * <p>
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 * 说明:
 * <p>
 * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-gap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/11/20 18:14
 */
public class Topic164 {
    /**
     * 暴力算法
     */
    public static class Solution1 {
        public int maximumGap(int[] nums) {
            Arrays.sort(nums);
            int maxGap = 0;
            for (int i = 1; i < nums.length; i++) {
                maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
            }
            return maxGap;
        }
    }

    /**
     * 基数排序，参考题解
     */
    public static class Solution2 {
        public int maximumGap(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return 0;
            }
            lsd(nums);
            int maxGap = 0;
            for (int i = 1; i < n; i++) {
                maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
            }
            return maxGap;
        }

        /**
         * 基数排序
         *
         * @param nums
         */
        private void lsd(int[] nums) {
            int n = nums.length;
            int max = nums[0];
            for (int i = 1; i < n; i++) {
                max = Math.max(max, nums[i]);
            }
            long exponent = 1;
            while (exponent <= max) {
                int[] cnt = new int[10];
                for (int i = 0; i < n; i++) {
                    int digit = nums[i] / (int) exponent % 10;
                    cnt[digit]++;
                }
                for (int i = 1; i < 10; i++) {
                    cnt[i] += cnt[i - 1];
                }
                int[] buf = new int[n];
                for (int i = n - 1; i >= 0; i--) {
                    int digit = nums[i] / (int) exponent % 10;
                    buf[-1 + cnt[digit]--] = nums[i];
                }
                System.arraycopy(buf, 0, nums, 0, n);
                exponent *= 10;
            }
        }
    }


    /**
     * 基于桶的算法，自己写的算法，参照了官方题解的思路
     */
    public static class Solution3 {
        public int maximumGap(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return 0;
            }

            int max = nums[0];
            int min = nums[0];
            for (int num : nums) {
                max = Math.max(max, num);
                min = Math.min(min, num);
            }
            if (max == min) {
                return 0;
            }
            int d = (max - min) / (n - 1);
            if (d * (n - 1) != max - min) {
                d += 1;
            }

            // buckets[n][0]表示第n个桶的最小值，buckets[n][1]表示第n个桶的最大值
            int[][] buckets = new int[n][2];
            for (int i = 0; i < n; i++) {
                Arrays.fill(buckets[i], -1);
            }
            for (int num : nums) {
                int index = (num - min) / d;
                if (buckets[index][0] == -1) {
                    buckets[index][0] = num;
                    buckets[index][1] = num;
                } else {
                    buckets[index][0] = Math.min(buckets[index][0], num);
                    buckets[index][1] = Math.max(buckets[index][1], num);
                }
            }
            int maxGap = d;
            int prevMax = buckets[0][1];
            for (int i = 1; i < n; i++) {
                if (buckets[i][0] != -1) {
                    maxGap = Math.max(maxGap, buckets[i][0] - prevMax);
                    prevMax = buckets[i][1];
                }
            }
            return maxGap;
        }
    }
}
