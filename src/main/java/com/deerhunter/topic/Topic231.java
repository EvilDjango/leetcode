package com.deerhunter.topic;

/**
 * 231. 2 的幂
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 1
 * 输出：true
 * 解释：20 = 1
 * 示例 2：
 * <p>
 * 输入：n = 16
 * 输出：true
 * 解释：24 = 16
 * 示例 3：
 * <p>
 * 输入：n = 3
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：n = 4
 * 输出：true
 * 示例 5：
 * <p>
 * 输入：n = 5
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * -231 <= n <= 231 - 1
 * <p>
 * <p>
 * 进阶：你能够不使用循环/递归解决此问题吗？
 * <p>
 * 通过次数147,415提交次数294,056
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 6/29/21 2:28 PM
 */
public class Topic231 {
    public static class Solution1 {
        public boolean isPowerOfTwo(int n) {
            if (n <= 0) {
                return false;
            }
            while (n > 1) {
                if (n % 2 != 0) {
                    return false;
                }
                n /= 2;
            }
            return true;
        }
    }

    /**
     * 二进制
     */
    public static class Solution2 {
        public boolean isPowerOfTwo(int n) {
            if (n <= 0) {
                return false;
            }
            while ((n & 1) == 1) {
                n >>= 1;
            }
            return n == 0;
        }
    }
}
