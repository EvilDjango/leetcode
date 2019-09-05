package com.deerhunter;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 示例 1:
 * <p>
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 * <p>
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-04
 */
public class Solution029 {
    public static int divide2(int dividend, int divisor) {
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        boolean positive = isQuotientPositive(dividend, divisor);
        int base = 0;
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
            dividend += positive ? -divisor : divisor;
            base++;
        }
        int quotient = divideRecursive(positive, dividend, divisor);
        quotient += base;

        return positive ? quotient : -quotient;
    }

    private static int divideRecursive(boolean positive, int dividend, int divisor) {
        int remove = divisor;
        if (Math.abs(remove) > Math.abs(dividend)) {
            return 0;
        }
        int quotient = 1;
        // 防止溢出
        while (Math.abs(dividend) - Math.abs(remove) >= Math.abs(remove)) {
            remove += remove;
            quotient += quotient;
        }
        int remainder = positive ? dividend - remove : dividend + remove;
        return quotient + divideRecursive(positive, remainder, divisor);
    }


    public static int divide(int dividend, int divisor) {
        boolean positive = isQuotientPositive(dividend, divisor);
        int quotient = 0;
        if (dividend == Integer.MIN_VALUE) {
            quotient++;
            dividend += positive ? -divisor : divisor;
        }

        while (Math.abs(dividend) >= Math.abs(divisor)) {
            dividend += positive ? -divisor : divisor;
            quotient++;
        }
        return positive ? quotient : -quotient;
    }

    public static boolean isQuotientPositive(int dividend, int divisor) {
        if (dividend >= 0) {
            return divisor > 0;
        }
        return divisor < 0;
    }
}