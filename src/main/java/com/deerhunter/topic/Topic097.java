package com.deerhunter.topic;

/**
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interleaving-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/6 22:42
 */
public class Topic097 {
    /**
     * 暴力算法
     */
    public static class Solution1 {
        public static boolean isInterleave(String s1, String s2, String s3) {
            if (s3.length() != s1.length() + s2.length()) {
                return false;
            }
            return isInterleave(s3.toCharArray(), 0, s1.toCharArray(), 0, s2.toCharArray(), new boolean[s3.length()]);

        }

        private static boolean isInterleave(char[] whole, int wholeIndex, char[] part1, int index, char[] part2, boolean[] used) {
            if (index == part1.length) {
                int part2Index = 0;
                for (int i = 0; i < used.length; i++) {
                    if (!used[i]) {
                        if (whole[i] != part2[part2Index++]) {
                            return false;
                        }
                    }
                }
                return true;
            }
            char c = part1[index];
            for (int i = wholeIndex; i < used.length; i++) {
                if (!used[i] && whole[i] == c) {
                    used[i] = true;
                    if (isInterleave(whole, i + 1, part1, index + 1, part2, used)) {
                        return true;
                    }
                    used[i] = false;
                }
            }
            return false;
        }
    }

    /**
     * 动态规划
     */
    public static class Solution2 {
        public static boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }
            boolean[][][] dp = new boolean[s3.length() + 1][s2.length() + 1][s1.length() + 1];
            dp[0][0][0] = true;
            for (int i = 1; i <= s3.length(); i++) {
                for (int j = 0; j <= s2.length(); j++) {
                    for (int k = 0; k <= s1.length(); k++) {
                        if (i != j + k) {
                            continue;
                        }
                        dp[i][j][k] = (j > 0 && dp[i - 1][j - 1][k] && s3.charAt(i - 1) == s2.charAt(j - 1)) || (k > 0 && dp[i - 1][j][k - 1] && s3.charAt(i - 1) == s1.charAt(k - 1));
                    }
                }
            }
            return dp[s3.length()][s2.length()][s1.length()];
        }
    }

    /**
     * 参考题解写的动态规划，本质上跟解法二是一样的，但是简洁很多
     */
    public static class Solution3 {
        public static boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }
            boolean[] dp = new boolean[s2.length() + 1];
            for (int i = 0; i <= s1.length(); i++) {
                for (int j = 0; j <= s2.length(); j++) {
                    if (i + j == 0) {
                        dp[j] = true;
                        continue;
                    }
                    dp[j] = (i > 0 && dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (j > 0 && dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
            return dp[s2.length()];
        }
    }
}
