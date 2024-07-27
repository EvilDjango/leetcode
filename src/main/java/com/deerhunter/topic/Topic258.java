package com.deerhunter.topic;

/**
 * 258. 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 * <p>
 * 示例:
 * <p>
 * 输入: 38
 * 输出: 2
 * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
 * 进阶:
 * 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
 * <p>
 * 通过次数72,455提交次数104,964
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/6/21 3:47 PM
 */
public class Topic258 {
    public static class Solution1 {
        public int addDigits(int num) {
            while (num >= 10) {
                int i = 0;
                while (num > 0) {
                    i += num % 10;
                    num /= 10;
                }
                num = i;
            }
            return num;
        }
    }

    public static class Solution2 {
        public int addDigits(int num) {
            return (num-1) % 9+1;
        }
    }
}
