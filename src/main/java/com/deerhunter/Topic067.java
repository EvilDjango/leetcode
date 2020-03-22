package com.deerhunter;

import java.math.BigInteger;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 输入为非空字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-21
 */
public class Topic067 {
    public static class Solution1 {
        public String addBinary(String a, String b) {
            int len = Math.max(a.length(), b.length());
            StringBuilder sb = new StringBuilder();
            // 进位
            boolean carry = false;
            for (int i = 0; i < len; i++) {
                int indexA = a.length() - i - 1;
                int indexB = b.length() - i - 1;
                byte byteA = (byte) (indexA < 0 ? 0 : Character.digit(a.charAt(indexA), 2));
                byte byteB = (byte) (indexB < 0 ? 0 : Character.digit(b.charAt(indexB), 2));
                byte sum = (byte) (carry ? byteA + byteB + 1 : byteA + byteB);
                carry = false;
                if (sum >= 2) {
                    carry = true;
                    sb.append(sum - 2);
                } else {
                    sb.append(sum);
                }
            }
            if (carry) {
                sb.append(1);
            }
            return sb.reverse().toString();
        }

    }

    /**
     * 参考官方题解：经典算法
     */
    public static class Solution2 {
        public String addBinary(String a, String b) {
            int len = Math.max(a.length(), b.length());
            StringBuilder sb = new StringBuilder();
            // 进位
            int carry = 0;
            for (int i = 0; i < len; i++) {
                int indexA = a.length() - i - 1;
                int indexB = b.length() - i - 1;
                if (indexA > -1 && a.charAt(indexA) == '1') {
                    carry++;
                }
                if (indexB > -1 && b.charAt(indexB) == '1') {
                    carry++;
                }
                sb.append(carry % 2);
                carry /= 2;
            }
            if (carry > 0) {
                sb.append(carry);
            }
            return sb.reverse().toString();
        }

    }

    /**
     * 位运算解法,由于测试用例的位数会超出64位，所以未ac
     */
    public static class Solution3 {
        public String addBinary(String a, String b) {
            long m = parseBinary(a);
            long n = parseBinary(b);
            long result = addBinary(m, n);
            return toBinary(result);
        }

        private long addBinary(long m, long n) {
            long answerWithoutCarry = m ^ n;
            long carry = (m & n) << 1;
            return carry == 0 ? answerWithoutCarry : addBinary(answerWithoutCarry, carry);
        }

        public long parseBinary(String s) {
            long ret = 0;
            long base = 1;
            for (int i = 0; i < s.length(); i++) {
                int index = s.length() - i - 1;
                ret += (s.charAt(index) - '0') * base;
                base *= 2;
            }
            return ret;
        }

        public String toBinary(long i) {
            if (i == 0) {
                return "0";
            }
            StringBuilder sb = new StringBuilder();
            while (i > 0) {
                sb.append(i % 2);
                i /= 2;
            }
            return sb.reverse().toString();
        }

    }

    /**
     * 位运算解法，复刻官方解法
     */
    public static class Solution4 {
        public String addBinary(String a, String b) {
            BigInteger m = new BigInteger(a, 2);
            BigInteger n = new BigInteger(b, 2);
            BigInteger zero = new BigInteger("0", 2);
            while (n.compareTo(zero) != 0) {
                BigInteger answer = m.xor(n);
                BigInteger carry = m.and(n).shiftLeft(1);
                m = answer;
                n = carry;
            }
            return m.toString(2);
        }
    }

}
