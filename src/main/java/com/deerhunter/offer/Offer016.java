package com.deerhunter.offer;

/**
 * 剑指 Offer 16. 数值的整数次方
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 * <p>
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 * <p>
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * <p>
 * 提示：
 * <p>
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 * <p>
 * <p>
 * 注意：本题与主站 50 题相同：https://leetcode-cn.com/problems/powx-n/
 * <p>
 * 通过次数174,036提交次数504,178
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/17 下午1:56
 */
public class Offer016 {
    /**
     * 快速幂+递归
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        long N = n;
        return N > 0 ? myPowLong(x, N) : 1 / myPowLong(x, -N);
    }

    public double myPowLong(double x, long n) {
        if (n == 0) {
            return 1;
        }
        double y = myPowLong(x, n >> 1);
        return (n & 1) == 0 ? y * y : y * y * x;
    }

    /**
     * 快速幂+迭代
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow2(double x, int n) {
        long N = n;
        return N > 0 ? myPowLong2(x, N) : 1 / myPowLong2(x, -N);
    }

    private double myPowLong2(double x, long n) {
        double ans = 1;
        double pow = x;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans *= pow;
            }
            pow *= pow;
            n >>= 1;
        }
        return ans;
    }
}
