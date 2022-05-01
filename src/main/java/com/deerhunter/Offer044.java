package com.deerhunter;

/**
 * 剑指 Offer 44. 数字序列中某一位的数字
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 * <p>
 * 请写一个函数，求任意第n位对应的数字。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：n = 11
 * 输出：0
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= n < 2^31
 * 注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/
 * <p>
 * 通过次数76,838提交次数180,676
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/1 上午11:37
 */
public class Offer044 {
    /**
     * 自己写的
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        if (n < 10) {
            return n;
        }
        long N = n;
        int bits = 1;
        long counts = 10;
        long length = 10;
        while (true) {
            long nextCounts = counts + counts * 9;
            long nextLength = length + counts * 9 * (bits + 1);
            if (nextLength >= N + 1) {
                break;
            }
            counts = nextCounts;
            length = nextLength;
            bits++;
        }
        counts += (N + 1 - length) / (bits + 1);
        long remain = (N + 1 - length) % (bits + 1);
        if (remain == 0) {
            return (int) ((counts - 1) % 10);
        }
        return String.valueOf(counts).charAt((int) remain - 1) - '0';
    }

    /**
     * 参考优秀题解，思路与方法一几乎一直，但是代码更简洁
     *
     * @param n
     * @return
     */
    public int findNthDigit2(int n) {
        int bits = 1;
        long start = 1;
        long count = 9;
        while (n > count) {
            n -= count;
            bits++;
            start *= 10;
            count = bits * start * 9;
        }
        long num = start + (n - 1) / bits;
        return Long.toString(num).charAt((n - 1) % bits) - '0';
    }
}
