package com.deerhunter;

/**
 * 剑指 Offer 19. 正则表达式匹配
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 * 注意：本题与主站 10 题相同：https://leetcode-cn.com/problems/regular-expression-matching/
 * <p>
 * 通过次数89,558提交次数231,317
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/16 下午11:51
 */
public class Offer019 {
    /**
     * 动态规划，自己写的
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // dp[i][j]表示s[0,i)可以匹配p[0,j)
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            dp[0][i + 1] = p.charAt(i) == '*' && dp[0][i - 1];
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char pChar = p.charAt(j);
                if (pChar == '.' || pChar == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (pChar == '*') {
                    // *可以匹配0个或者1个
                    dp[i + 1][j + 1] = dp[i + 1][j - 1] || ((p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i)) && dp[i][j + 1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 动态规划，参考官方题解，更简洁一些
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2] || (match(s, i, p, j - 1) && dp[i - 1][j]);
                } else if (match(s, i, p, j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    private boolean match(String s, int i, String pattern, int j) {
        return i > 0 && (pattern.charAt(j - 1) == '.' || pattern.charAt(j - 1) == s.charAt(i - 1));
    }
}
