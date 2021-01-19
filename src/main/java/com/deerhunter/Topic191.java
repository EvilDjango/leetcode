package com.deerhunter;

import java.util.HashMap;
import java.util.Map;

/**
 * 191. 位1的个数
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 如果多次调用这个函数，你将如何优化你的算法？
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * 示例 2：
 * <p>
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 * 示例 3：
 * <p>
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 输入必须是长度为 32 的 二进制串 。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/1/14 18:27
 */
public class Topic191 {
    public static class Solution1 {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int count = 0;
            for (int i = 0; i < 32; i++) {
                count += n & 1;
                n >>= 1;
            }
            return count;
        }
    }

    public static class Solution2 {
        private Map<Integer, Integer> counts = new HashMap<>();

        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int res = 0;
            for (int i = 0; i < 4; i++) {
                res += getCountOfOne(n & 0xff);
                n >>= 8;
            }
            return res;
        }

        private int getCountOfOne(int i) {
            if (!counts.containsKey(i)) {
                int count = 0;
                for (int j = 0, k = i; j < 8; j++) {
                    count += k & 1;
                    k >>= 1;
                }
                counts.put(i, count);
            }
            return counts.get(i);
        }
    }

    public static class Solution3 {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int bits = 0;
            while (n != 0) {
                bits++;
                n &= (n - 1);
            }
            return bits;
        }
    }
}
