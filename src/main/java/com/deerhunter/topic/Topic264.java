package com.deerhunter.topic;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 264. 丑数 II
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * <p>
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 1690
 * 通过次数86,819提交次数151,638
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/9/21 2:10 PM
 */
public class Topic264 {
    /**
     * 暴力算法，会超时
     */
    public static class Solution1 {
        public int nthUglyNumber(int n) {
            int i = 1, j = 0;
            while (j < n) {
                if (isUgly(i)) {
                    j++;
                }
                i++;
            }
            return i - 1;
        }

        private boolean isUgly(int num) {
            if (num <= 0) {
                return false;
            }
            num = removeFactor(num, 2);
            num = removeFactor(num, 3);
            return removeFactor(num, 5) == 1;
        }

        private int removeFactor(int num, int factor) {
            while (num % factor != 0) {
                num /= factor;
            }
            return num;
        }
    }

    /**
     * 最小堆
     */
    public static class Solution2 {
        public int nthUglyNumber(int n) {
            PriorityQueue<Long> queue = new PriorityQueue<>();
            Set<Long> nums = new HashSet<>();
            queue.add(1L);
            nums.add(1L);
            int i = 1;
            int[] factors = {2, 3, 5};
            while (i < n) {
                long min = queue.remove();
                for (int factor : factors) {
                    long multiple = factor * min;
                    if (!nums.contains(multiple)) {
                        queue.add(multiple);
                        nums.add(multiple);
                    }
                }
                i++;
            }
            return Math.toIntExact(queue.remove());
        }
    }

    /**
     * 动态规划
     */
    public static class Solution3 {
        public int nthUglyNumber(int n) {
            int[] nums = new int[n];
            nums[0] = 1;
            int p2 = 0, p3 = 0, p5 = 0;
            for (int i = 1; i < n; i++) {
                nums[i] = Math.min(Math.min(nums[p2] * 2, nums[p3] * 3), nums[p5] * 5);
                if (nums[i] == nums[p2] * 2) {
                    p2++;
                }
                if (nums[i] == nums[p3] * 3) {
                    p3++;
                }
                if (nums[i] == nums[p5] * 5) {
                    p5++;
                }
            }
            return nums[n - 1];
        }
    }
}
