package com.deerhunter;

import java.util.ArrayList;
import java.util.List;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-21
 */
public class Solution010 {
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
        boolean firstMatch = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch2(s, p.substring(2)) || firstMatch && isMatch2(s.substring(1), p);
        }
        return firstMatch && isMatch2(s.substring(1), p.substring(1));
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
}