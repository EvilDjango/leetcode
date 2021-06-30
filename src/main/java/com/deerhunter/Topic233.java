package com.deerhunter;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 233. 数字 1 的个数
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 13
 * 输出：6
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 2 * 109
 * 通过次数16,507提交次数40,775
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 6/30/21 11:53 AM
 */
public class Topic233 {
    /**
     * 暴力算法
     */
    public static class Solution1 {
        public static int countDigitOne(int n) {
            int ans = 0;
            for (int i = 1; i <= n; i++) {
                ans += Topic233.countDigitOne(i);
            }
            return ans;
        }
    }

    public static class Solution2 {
        public static int countDigitOne(int n) {
            int ans = 0;
            int base = 1;
            int i = n;
            while (i > 0) {
                ans += i / 10 * base;
                int digit = i % 10;
                if (digit == 1) {
                    ans += (n - base * i) + 1;
                } else if (digit > 1) {
                    ans += base;
                }
                i /= 10;
                base *= 10;
            }
            return ans;
        }
    }

    /**
     * 参考官方题解，本质上和Solution2是一样的
     */
    public static class Solution3 {
        public static int countDigitOne(int n) {
            int ans = 0;
            for (int i = 1; i <= n; i *= 10) {
                int divisor = i * 10;
                ans += n / divisor * i + Math.max(0, Math.min(n % divisor - i + 1, i));
            }
            return ans;
        }
    }

    protected static int countDigitOne(int i) {
        int count = 0;
        while (i > 0) {
            if (i % 10 == 1) {
                count++;
            }
            i /= 10;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
//        FileWriter writer = new FileWriter("/Users/django/test/countDigit.txt");
//        for (int i = 1; i <= 123; i++) {
//            int count = Solution1.countDigitOne(i);
//            String text = String.format("%d: %d\n", i, count);
//            System.out.println(text);
//            writer.write(text);
//        }
//        writer.flush();
//        writer.close();
        System.out.println(Solution1.countDigitOne(1231));
    }
}
