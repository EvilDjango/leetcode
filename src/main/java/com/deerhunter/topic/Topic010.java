package com.deerhunter.topic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
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
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-21
 */
public class Topic010 {
    // 根据官方题解自己写的解法：动态规划，从底向顶
    public boolean isMatch4(String s, String p) {
        boolean[][] f = new boolean[s.length() + 1][p.length() + 1];
        f[s.length()][p.length()] = true;
        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean firstMatch = i < s.length() && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i));
                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    f[i][j] = (firstMatch && f[i + 1][j]) || f[i][j + 2];
                } else {
                    f[i][j] = firstMatch && f[i + 1][j + 1];
                }
            }
        }
        return f[0][0];
    }

    // 根据官方题解自己写的解法：动态规划，从顶到底
    public boolean isMatch3(String s, String p) {
        Boolean[][] f = new Boolean[s.length() + 1][p.length() + 1];
        return dp(f, s, p, 0, 0);
    }

    private boolean dp(Boolean[][] f, String s, String p, int i, int j) {
        if (f[i][j] != null) {
            return f[i][j];
        }

        if (p.isEmpty()) {
            f[i][j] = s.isEmpty();
            return f[i][j];
        }

        boolean firstMatch = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        if (p.length() > 1 && p.charAt(1) == '*') {
            f[i][j] = firstMatch && dp(f, s.substring(1), p, i + 1, j) || dp(f, s, p.substring(2), i, j + 2);
            return f[i][j];
        }
        f[i][j] = dp(f, s.substring(1), p.substring(1), i + 1, j + 1);

        return f[i][j];
    }

    // 自己写的暴力穷举的方法，非常朴素，效率也非常低
    public boolean isMatch(String s, String p) {
        if (p.length() == 0 && s.length() == 0) {
            return true;
        }
        List<Element> elements = new ArrayList<>(p.length());
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            // 单独存在的星号，忽略
            if (c == '*') {
                continue;
            }

            boolean wildCard = c == '.';
            boolean single = i + 1 > p.length() - 1 || p.charAt(i + 1) != '*';
            elements.add(new Element(c, wildCard, single));
            if (!single) {
                i++;
            }
        }
        List<Character> seq = new ArrayList<>(elements.size());
        return dfs(elements, 0, seq, s);
    }

    // 根据官方题解自己写的回溯法解法
    public boolean isMatch2(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean firstMatch = s.length() > 0 && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0));
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch2(s, p.substring(2)) || (firstMatch && isMatch2(s.substring(1), p));
        }
        return firstMatch && isMatch2(s.substring(1), p.substring(1));
    }

    // 优化版的回溯法
    public boolean isMatch6(String s, String p) {
        return isMatch(s, p, 0, 0);
    }

    private boolean isMatch(String s, String p, int i, int j) {
        if (j == p.length()) {
            return i == s.length();
        }
        boolean firstMatch = i < s.length() && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j));
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            return isMatch(s, p, i, j + 2) || firstMatch && isMatch(s, p, i + 1, j);
        }
        return firstMatch && isMatch(s, p, i + 1, j + 1);
    }

    public boolean dfs(List<Element> elements, int i, List<Character> seq, String s) {
        int len = seq.size();
        if (i == elements.size()) {
            return false;
        }
        Element element = elements.get(i);

        if (element.single) {
            seq.add(element.c);
            if (seq.size() == s.length() && i == elements.size() - 1) {
                return equals(seq, s);
            }
            return dfs(elements, ++i, seq, s);
        } else {
            for (int j = 0; j <= s.length() - len; j++) {
                seq = seq.subList(0, len);
                for (int k = 0; k < j; k++) {
                    seq.add(element.c);
                }
                if (seq.size() == s.length()) {
                    return i == elements.size() - 1 && equals(seq, s);
                }
                if (dfs(elements, i + 1, seq, s)) {

                    return true;
                }
            }
        }
        return false;
    }

    public boolean isMatch5(String s, String p) {
        boolean[][] dp = new boolean[s.length()][p.length()];
        for (int i = 0, j = 0; i < s.length() || j < p.length(); ) {
            if (j == 0) {
                dp[i][j++] = i == 0;
                continue;
            }
            boolean match = i < s.length() && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i));
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                dp[i][j] = dp[i][j - 1] || match && (i == 0 || dp[i - 1][j]);
                dp[i][j + 1] = dp[i][j];
                j += 2;
            } else {
                dp[i][j] = match && (i == 0 || dp[i - 1][j - 1]);
                j++;
            }
            if (i < s.length()) {
                i++;
            }
        }
        return dp[s.length() - 1][p.length() - 1];
    }

    private boolean equals(List<Character> seq, String s) {
        for (int i = 0; i < seq.size(); i++) {
            char c = seq.get(i);
            if (c == '.') {
                continue;
            }
            if (c != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 正则表达式的一个基本单位:一个字符加上其后的数量单位.例如"f*"可以表示为new Element('f',false,true)
     */
    static class Element {
        private char c;
        private boolean wildCard;
        private boolean single;

        public Element(char c, boolean wildCard, boolean single) {
            this.c = c;
            this.wildCard = wildCard;
            this.single = single;
        }
    }

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> map = new HashMap<>();
        map.put("a", new HashMap<>());
        map.put("b", new HashMap<>());
        map.get("a").put("a1", 1);
        map.get("a").put("a2", 2);
        map.get("b").put("b1", 3);
        map.get("b").put("b2", 4);
        System.out.println(map);
    }
}
