package com.deerhunter.topic;

/**
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-24
 */
public class Topic069 {
    public static class Solution1 {
        public int mySqrt(int x) {
            if (x < 2) {
                return x;
            }
            int left = 0, right = x;
            while (left + 1 < right) {
                int middle = (right - left) / 2 + left;
                long pow = (long) middle * middle;
                if (pow > x) {
                    right = middle;
                } else if (pow < x) {
                    left = middle;
                } else {
                    return middle;
                }
            }
            return left;
        }
    }

    /**
     * 复制官方题解
     */
    public static class Solution2 {
        public int mySqrt(int x) {
            int left = (int) Math.pow(Math.E, 0.5 * Math.log(x));
            int right = left + 1;
            return (long) right * right > x ? left : right;
        }
    }

    /**
     * 牛顿法
     */
    public static class Solution3 {
        public int mySqrt(int x) {
            double x0;
            double x1 = x;
            do {
                x0 = x1;
                x1 = (x0 + x / x0) / 2.0;
            } while (Math.abs(x0 - x1) >= 1);

            return (int) x1;
        }
    }
}

