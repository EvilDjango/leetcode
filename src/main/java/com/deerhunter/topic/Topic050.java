package com.deerhunter.topic;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * <p>
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * <p>
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 * <p>
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-07
 */
public class Topic050 {
    // 暴力法
    public static class Solution1 {
        public double myPow(double x, int n) {
            int count = Math.abs(n);
            double product = 1.0;
            for (int i = 0; i < count; i++) {
                product *= x;
            }
            double result = n > 0 ? product : 1 / product;
            result = Double.parseDouble(String.format("%.5f", result));
            return result;
        }
    }


    // 快速幂算法（递归）
    public static class Solution2 {
        public double myPow(double x, int n) {
            long N = n;
            if (N < 0) {
                x = 1 / x;
                N = -N;
            }
            double result = doPow(x, N);
            result = Double.parseDouble(String.format("%.5f", result));
            return result;
        }

        private double doPow(double x, long n) {
            if (n == 0) {
                return 1;
            }
            if (n % 2 == 0) {
                double square = doPow(x, n / 2);
                return square * square;
            } else {
                return doPow(x, n - 1) * x;
            }
        }
    }

    // 快速幂算法（循环），自己写的
    public static class Solution3 {
        public double myPow(double x, int n) {
            long N = n;
            if (N < 0) {
                x = 1 / x;
                N = -N;
            }

            // 指数集合
            List<Long> exponents = new ArrayList<>();
            // 幂集合，与指数集合相对应
            List<Double> pows = new ArrayList<>();
            long exponent = 1;
            pows.add(x);
            exponents.add(1L);
            while (exponent < N) {
                exponent += exponent;
                double base = pows.get(pows.size() - 1);
                pows.add(base * base);
                exponents.add(exponent);
            }

            double result = 1;
            // 剩余的指数
            long remainExponent = N;
            for (int i = exponents.size() - 1; i >= 0; i--) {
                if (remainExponent >= exponents.get(i)) {
                    remainExponent -= exponents.get(i);
                    result *= pows.get(i);
                    if (remainExponent == 0) {
                        break;
                    }
                }
            }
            result = Double.parseDouble(String.format("%.5f", result));
            return result;
        }
    }

    // 快速幂算法（循环），官方解法，十分精妙。从本质上来讲，是我自己写的解法3的改进
    public static class Solution4 {
        public double myPow(double x, int n) {
            long N = n;
            if (N < 0) {
                x = 1 / x;
                N = -N;
            }

            double result = 1;
            double currentProduct = x;
            for (long i = N; i > 0; i /= 2) {
                if (i % 2 != 0) {
                    result *= currentProduct;
                }
                currentProduct *= currentProduct;
            }
            result = Double.parseDouble(String.format("%.5f", result));
            return result;
        }
    }
}
