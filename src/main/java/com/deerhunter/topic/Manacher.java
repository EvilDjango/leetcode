package com.deerhunter.topic;

/**
 * Oops, forgot to write comments. Good luck, bro.
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/21 19:43
 */
public class Manacher {
    public static int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int newLen = 2 * len + 3;
        int[] p = new int[newLen];
        char[] chars = new char[newLen];
        chars[0] = '^';
        chars[1] = '#';
        int index = 1;
        for (int i = 0; i < len; i++) {
            chars[++index] = s.charAt(i);
            chars[++index] = '#';
        }
        chars[++index] = '$';
        int mx = 0;
        int pivot = 0;
        int maxLen = 0;
        for (int i = 2; i < newLen - 2; i++) {
            if (i < mx) {
                p[i] = Math.min(p[2 * pivot - i], mx - i);
            } else {
                p[i] = 1;
            }

            while (chars[i - p[i]] == chars[i + p[i]]) {
                p[i]++;
            }
            if (mx < i + p[i]) {
                pivot = i;
                mx = i + p[i];
            }
            maxLen = Math.max(maxLen, p[i] - 1);
        }
        return maxLen;
    }
}
