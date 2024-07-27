package com.deerhunter.offer;

/**
 * 剑指 Offer 43. 1～n 整数中 1 出现的次数
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 * <p>
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 12
 * 输出：5
 * 示例 2：
 * <p>
 * 输入：n = 13
 * 输出：6
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= n < 2^31
 * 注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/
 * <p>
 * 通过次数65,967提交次数132,273
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/2 上午9:49
 */
public class Offer043 {
    /**
     * 依次统计个位，十位，百位。。。出现的1的次数
     *
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        int count = 0;
        long base = 1;
        while (base <= n) {
            count += n / base / 10 * base;
            if (n % (base * 10) >= base) {
                count += Math.min(base, n % (base * 10) - base + 1);
            }
            base *= 10;
        }
        return count;
    }
}
