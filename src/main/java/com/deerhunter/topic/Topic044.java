package com.deerhunter.topic;

import java.util.Objects;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 * <p>
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 * <p>
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 * <p>
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输入: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wildcard-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-11-21
 */
public class Topic044 {
    private Boolean[][] dp;

    public boolean isMatch(String s, String p) {
        p = removeDuplicateStars(p);
        dp = new Boolean[s.length()][p.length()];
        return doMatch(s, p);
    }

    private String removeDuplicateStars(String p) {
        if (p == null || p.length() <= 1) {
            return p;
        }
        StringBuilder stringBuilder = new StringBuilder();
        char last = p.charAt(0);
        stringBuilder.append(last);
        for (int i = 1; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == last && last == '*') {
                continue;
            }
            stringBuilder.append(c);
            last = c;
        }
        return stringBuilder.toString();
    }

    private boolean doMatch(String s, String p) {
        if (p == null || s == null) {
            throw new NullPointerException();
        }

        if (s.equals(p) || "*".equals(p)) {
            return true;
        }
        if (p.isEmpty() || s.isEmpty()) {
            return false;
        }

        int sLen = s.length();
        int pLen = p.length();
        if (dp[sLen - 1][pLen - 1] != null) {
            return dp[sLen - 1][pLen - 1];
        }
        boolean match;
        if (p.endsWith("*")) {
            match = doMatch(s, p.substring(0, pLen - 1)) || doMatch(s.substring(0, sLen - 1), p);
        } else if (p.endsWith("?")) {
            match = doMatch(s.substring(0, sLen - 1), p.substring(0, pLen - 1));
        } else {
            match = s.charAt(sLen - 1) == p.charAt(pLen - 1) && doMatch(s.substring(0, sLen - 1), p.substring(0, pLen - 1));
        }
        dp[sLen - 1][pLen - 1] = match;
        return match;
    }

    public boolean isMatch2(String s, String p) {
        if (s.equals(p) || "*".equals(p)) {
            return true;
        }
        if (p.isEmpty() || s.isEmpty()) {
            return false;
        }
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[pLen + 1][sLen + 1];
        dp[0][0] = true;
        for (int pIndex = 1; pIndex < pLen + 1; pIndex++) {
            char c = p.charAt(pIndex - 1);
            if (c == '*') {
                int sIndex = 1;
                while (sIndex < sLen + 1 && !dp[pIndex - 1][sIndex - 1]) {
                    sIndex++;
                }
                dp[pIndex][sIndex - 1] = dp[pIndex - 1][sIndex - 1];
                while (sIndex < sLen + 1) {
                    dp[pIndex][sIndex++] = true;
                }
            } else if (c == '?') {
                for (int j = 1; j < sLen + 1; j++) {
                    dp[pIndex][j] = dp[pIndex - 1][j - 1];
                }
            } else {
                for (int j = 1; j < sLen + 1; j++) {
                    dp[pIndex][j] = c == s.charAt(j - 1) && dp[pIndex - 1][j - 1];
                }
            }
        }

        return dp[p.length()][s.length()];
    }


    /**
     * 回溯法递归版（LeetCode提交时会超时）
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch3(String s, String p) {
        if (Objects.equals(s, p)) {
            return true;
        }
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        char c = p.charAt(0);
        if (c == '*') {
            for (int i = 0; i < s.length() + 1; i++) {
                if (isMatch3(s.substring(i), p.substring(1))) {
                    return true;
                }
            }
            return false;
        } else if (c == '?') {
            return s.length() > 0 && isMatch3(s.substring(1), p.substring(1));
        } else {
            return s.length() > 0 && s.charAt(0) == c && isMatch3(s.substring(1), p.substring(1));
        }

    }

    /**
     * 双指针法
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch4(String s, String p) {
        int pIdx = 0, sIdx = 0, startIndex = -1, sTmpIdx = -1, sLen = s.length(), pLen = p.length();
        while (sIdx < sLen) {
            if (pIdx < pLen && p.charAt(pIdx) == '*') {
                startIndex = pIdx;
                sTmpIdx = sIdx;
                pIdx++;
            } else if (
                    pIdx < pLen &&
                            (p.charAt(pIdx) == '?'
                                    || p.charAt(pIdx) == s.charAt(sIdx))) {
                pIdx++;
                sIdx++;
            } else if (startIndex == -1) {
                return false;
            } else {
                pIdx = startIndex + 1;
                sIdx = sTmpIdx + 1;
                sTmpIdx = sIdx;
            }
        }
        while (pIdx < pLen) {
            if (p.charAt(pIdx++) != '*') {
                return false;
            }
        }
        return true;
    }
}
