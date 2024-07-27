package com.deerhunter.topic;

import java.util.Objects;

/**
 * 最长回文子串
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-09
 */
public class Topic005 {
    // 中心扩展算法
    public String longestPalindrome4(String s) {
        if (null == s || s.length() == 0) {
            return "";
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 以i为中心的奇数长度的最大回文
            int len1 = expandAroundCenter(s, i, i);
            //  以i和i+1的间隙为中心的偶数长度的最大回文
            int len2 = expandAroundCenter(s, i, i + 1);
            int maxLen = Math.max(len1, len2);
            if (maxLen > end - start + 1) {
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public String longestPalindrome3(String s) {

        int maxLen = 1;
        int start = 0;
        int end = 0;
        boolean[][] f = new boolean[s.length()][s.length()];
        for (int len = 1; len <= s.length(); len++) {
            for (int i = 0; i < s.length(); i++) {
                if (1 == len) {
                    f[i][i] = true;
                } else {
                    int e;
                    e = i + len / 2;
                    int st = e + 1 - len;
                    if (st < 0 || e >= s.length()) {
                        continue;
                    }
                    if (s.charAt(st) == s.charAt(e) && (st + 1 == e || f[st + 1][e - 1])) {
                        f[st][e] = true;
                        maxLen = Math.max(len, maxLen);
                        start = st;
                        end = e;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }

    public String longestPalindrome1(String s) {
        if (null == s) {
            return null;
        }
        if (isPalindrome(s)) {
            return s;
        }
        String[] palindromes = new String[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String fragment = s.substring(i, j);
                if (isPalindrome(fragment)) {
                    palindromes[fragment.length() - 1] = fragment;
                }
            }
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            if (palindromes[i] != null) {
                return palindromes[i];
            }
        }
        return "";
    }

    public String longestPalindrome2(String s) {
        if (null == s) {
            return null;
        }
        for (int length = s.length(); length > 0; length--) {
            for (int j = 0; j + length <= s.length(); j++) {
                String fragment = s.substring(j, j + length);
                if (isPalindrome(fragment)) {
                    return fragment;
                }
            }
        }
        return "";
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (!Objects.equals(s.charAt(left), s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


}
