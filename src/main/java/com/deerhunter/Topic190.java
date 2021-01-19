package com.deerhunter;

import java.util.HashMap;
import java.util.Map;

/**
 * 190. Reverse Bits
 * Easy
 * <p>
 * 1482
 * <p>
 * 500
 * <p>
 * Add to List
 * <p>
 * Share
 * Reverse bits of a given 32 bits unsigned integer.
 * <p>
 * Note:
 * <p>
 * Note that in some languages such as Java, there is no unsigned integer type. In this case, both input and output will be given as a signed integer type. They should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above, the input represents the signed integer -3 and the output represents the signed integer -1073741825.
 * Follow up:
 * <p>
 * If this function is called many times, how would you optimize it?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 00000010100101000001111010011100
 * Output:    964176192 (00111001011110000010100101000000)
 * Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
 * Example 2:
 * <p>
 * Input: n = 11111111111111111111111111111101
 * Output:   3221225471 (10111111111111111111111111111111)
 * Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10111111111111111111111111111111.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The input must be a binary string of length 32
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/1/12 11:53
 */
public class Topic190 {
    public static class Solution1 {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            int res = 0;
            for (int i = 0; i < 32; i++) {
                int bit = n & 1;
                res = res << 1 | bit;
                n >>= 1;
                if (n == 0) {
                    res <<= 32 - i - 1;
                    break;
                }
            }
            return res;
        }
    }

    /**
     * 将int拆分为4个byte，并缓存每个byte的转换结果
     */
    public static class Solution2 {
        private Map<Integer, Integer> cache = new HashMap<>();

        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            int res = 0;
            for (int i = 0; i < 4; i++) {
                res <<= 8;
                res |= reverseByte(n & 0xff);
                n >>= 8;
            }
            return res;
        }

        private int reverseByte(int b) {
            if (!cache.containsKey(b)) {
                cache.put(b, (int) ((b * 0x0202020202L & 0x010884422010L) % 1023));
            }
            return cache.get(b);
        }
    }

    /**
     * 这个方法是错的，但我不知道为什么是错的
     */
    public static class Solution3 {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            return reverseBits(n, 32);
        }

        private int reverseBits(int n, int bits) {
            if (bits == 1) {
                return n;
            }
            int half = bits / 2;
            int left = n >>> half;
            int right = n & (1 << half - 1);
            return reverseBits(right, half) << half | reverseBits(left, half);
        }
    }

    /**
     * 掩码和移位操作
     */
    public static class Solution4 {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            n = (n >>> 16) | (n << 16);
            n = ((n & 0xff00ff00) >>> 8 )| ((n & 0x00ff00ff) << 8);
            n = ((n & 0xf0f0f0f0) >>> 4 )| ((n & 0x0f0f0f0f) << 4);
            n = ((n & 0xcccccccc) >>> 2 )| ((n & 0x33333333) << 2);
            n = ((n & 0xaaaaaaaa) >>> 1 )| ((n & 0x55555555) << 1);
            return n;
        }
    }

}
