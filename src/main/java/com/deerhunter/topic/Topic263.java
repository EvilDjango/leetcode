package com.deerhunter.topic;

/**
 * 263. 丑数
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 6
 * 输出：true
 * 解释：6 = 2 × 3
 * 示例 2：
 * <p>
 * 输入：n = 8
 * 输出：true
 * 解释：8 = 2 × 2 × 2
 * 示例 3：
 * <p>
 * 输入：n = 14
 * 输出：false
 * 解释：14 不是丑数，因为它包含了另外一个质因数 7 。
 * 示例 4：
 * <p>
 * 输入：n = 1
 * 输出：true
 * 解释：1 通常被视为丑数。
 * <p>
 * <p>
 * 提示：
 * <p>
 * -231 <= n <= 231 - 1
 * 通过次数98,760提交次数191,803
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/7/21 5:41 PM
 */
public class Topic263 {
    public static class Solution {
        public boolean isUgly(int num) {
            if (num <= 0) {
                return false;
            }
            num = removeFactor(num, 2);
            num = removeFactor(num, 3);
            return removeFactor(num, 5) == 1;
        }

        public int removeFactor(int num, int factor) {
            while (num % factor == 0) {
                num /= factor;
            }
            return num;
        }

        public String factorization(int num) {
            StringBuilder sb = new StringBuilder();
            sb.append(num);
            sb.append("  (");
            int[] factors = {2, 3, 5};
            for (int factor : factors) {
                while (num % factor == 0) {
                    sb.append(factor);
                    sb.append(" ");
                    num /= factor;
                }
            }
            sb.append(')');
            return sb.toString();
        }
    }
}
