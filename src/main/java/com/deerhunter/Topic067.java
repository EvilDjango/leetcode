package com.deerhunter;

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
}
