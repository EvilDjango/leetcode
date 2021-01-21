package com.deerhunter;

/**
 * 201. Bitwise AND of Numbers Range
 * Medium
 * <p>
 * 1244
 * <p>
 * 139
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 * <p>
 * Example 1:
 * <p>
 * Input: [5,7]
 * Output: 4
 * Example 2:
 * <p>
 * Input: [0,1]
 * Output: 0
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/1/21 14:13
 */
public class Topic201 {
    /**
     * 暴力算法，依次检查32位中每一位是否有0
     */
    public static class Solution1 {
        public int rangeBitwiseAnd(int m, int n) {
            if (m == 0) {
                return 0;
            }
            if (m == Integer.MAX_VALUE) {
                return m;
            }
            int res = 0;
            for (int i = 0; i < 32; i++) {
                int bit = m & 1;
                if (bit == 1 && m + 1 <= n) {
                    bit = 0;
                }
                res += bit << i;
                m >>>= 1;
                n >>>= 1;
            }
            return res;
        }
    }

    /**
     * 通过移位查找最大公共前缀
     */
    public static class Solution2 {
        public int rangeBitwiseAnd(int m, int n) {
            int i = 0;
            while (m != n) {
                m >>>= 1;
                n >>>= 1;
                i++;
            }
            return m << i;
        }
    }

    /**
     * Brian Kernighan 算法查找最大公共前缀
     */
    public static class Solution3 {
        public int rangeBitwiseAnd(int m, int n) {
            while (m < n) {
                // 抹去最右边的 1
                n = n & (n - 1);
            }
            return n;
        }
    }
}
