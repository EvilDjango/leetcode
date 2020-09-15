package com.deerhunter;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
 * <p>
 * 一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * <p>
 * 题目数据保证答案符合 32 位带符号整数范围。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：S = "rabbbit", T = "rabbit"
 * 输出：3
 * 解释：
 * <p>
 * 如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * <p>
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 示例 2：
 * <p>
 * 输入：S = "babgbag", T = "bag"
 * 输出：5
 * 解释：
 * <p>
 * 如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * <p>
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 * ^  ^^
 * babgbag
 * ^^^
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distinct-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/14 19:15
 */
public class Topic115 {
    /**
     * 暴力回溯
     */
    public static class Solution1 {
        private static int count;

        public static int numDistinct(String s, String t) {
            count = 0;
            search(s, 0, t, 0);
            return count;
        }

        private static void search(String s, int i, String t, int j) {
            if (j == t.length()) {
                count++;
                return;
            }
            for (int k = i; k < s.length(); k++) {
                if (s.charAt(k) == t.charAt(j)) {
                    search(s, k + 1, t, j + 1);
                }
            }
        }
    }


    /**
     * 动态规划
     */
    public static class Solution2 {
        public static int numDistinct(String s, String t) {
            if (t.length() > s.length() || s.length() == 0) {
                return 0;
            }
            int[][] dp = new int[t.length()][s.length()];
            dp[0][0] = t.charAt(0) == s.charAt(0) ? 1 : 0;
            for (int i = 1; i < s.length(); i++) {
                dp[0][i] = t.charAt(0) == s.charAt(i) ? dp[0][i - 1] + 1 : dp[0][i - 1];
            }
            for (int i = 1; i < t.length(); i++) {
                for (int j = 1; j < s.length(); j++) {
                    if (t.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }

                }
            }
            return dp[t.length() - 1][s.length() - 1];
        }
    }
}
