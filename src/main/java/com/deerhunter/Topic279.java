package com.deerhunter;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * <p>
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 * <p>
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 104
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/14/21 10:21 AM
 */
public class Topic279 {
    public static class Solution1 {
        public int numSquares(int n) {
            int[] dp = new int[n + 1];
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                int sqrt = (int) Math.sqrt(i);
                if (i - sqrt * sqrt == 0) {
                    dp[i] = 1;
                    continue;
                }
                int min = i;
                for (int j = 1; j <= i / 2; j++) {
                    min = Math.min(min, dp[i - j] + dp[j]);
                }
                dp[i] = min;
            }
            return dp[n];
        }
    }

    public static class Solution2 {
        public int numSquares(int n) {
            int[] dp = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                dp[i] = Integer.MAX_VALUE;
                for (int j = 1; j * j <= i; j++) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }
            return dp[n];
        }
    }

    /**
     * 利用四平方和定理
     */
    public static class Solution3 {
        public int numSquares(int n) {
            if (isPerfectSquare(n)) {
                return 1;
            }
            if (checkAnswer4(n)) {
                return 4;
            }
            for (int i = 1; i * i < n; i++) {
                if (isPerfectSquare(n - i * i)) {
                    return 2;
                }
            }
            return 3;
        }

        private boolean checkAnswer4(int n) {
            while (n % 4 == 0) {
                n /= 4;
            }
            return n % 8 == 7;
        }

        private boolean isPerfectSquare(int n) {
            int sqrt = (int) Math.sqrt(n);
            return n == sqrt * sqrt;
        }
    }
}
