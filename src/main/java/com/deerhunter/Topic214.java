package com.deerhunter;

import java.util.Arrays;

/**
 * 214. Shortest Palindrome
 * Hard
 * <p>
 * 1525
 * <p>
 * 150
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a string s, you can convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aacecaaa"
 * Output: "aaacecaaa"
 * Example 2:
 * <p>
 * Input: s = "abcd"
 * Output: "dcbabcd"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 5 * 104
 * s consists of lowercase English letters only.
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/2/26 14:39
 */
public class Topic214 {
    /**
     * 暴力算法
     */
    public static class Solution1 {
        public String shortestPalindrome(String s) {
            int len = s.length();
            String rev = new StringBuilder(s).reverse().toString();
            for (int i = 0; i < len; i++) {
                if (s.substring(0, len - i).equals(rev.substring(i, len))) {
                    return rev.substring(0, i) + s;
                }
            }
            return "";
        }
    }

    /**
     * 双指针+递归
     */
    public static class Solution2 {
        public String shortestPalindrome(String s) {
            return shortestPalindrome(s, s.length());
        }

        private String shortestPalindrome(String s, int right) {
            int i = 0;
            for (int j = right - 1; j >= 0; j--) {
                if (s.charAt(j) == s.charAt(i)) {
                    i++;
                }
            }
            if (i == right) {
                return s.substring(0, right);
            }
            String tail = s.substring(i, right);
            return new StringBuilder(tail).reverse().toString() + shortestPalindrome(s, i) + tail;
        }
    }

    /**
     * 简单的递归
     */
    public static class Solution3 {
        public String shortestPalindrome(String s) {
            return shortestPalindrome(s, s.length());
        }

        private String shortestPalindrome(String s, int length) {
            int l = 0;
            int r = length - 1;
            boolean isPalindrome = true;
            while (l < r) {
                if (s.charAt(l++) != s.charAt(r--)) {
                    isPalindrome = false;
                    break;
                }
            }
            if (isPalindrome) {
                return s.substring(0, length);
            }
            return s.charAt(length - 1) + shortestPalindrome(s, length - 1) + s.charAt(length - 1);
        }
    }

    /**
     * kmp算法
     */
    public static class Solution4 {
        public String shortestPalindrome(String s) {
            String rev = new StringBuilder(s).reverse().toString();
            int i = 1;
            int j = 0;
            String s2 = s + "#" + rev;
            int len = s2.length();
            int[] pmt = new int[len];
            while (i < len) {
                if (s2.charAt(i) == s2.charAt(j)) {
                    pmt[i++] = ++j;
                } else if (j == 0) {
                    i++;
                } else {
                    j = pmt[j - 1];
                }
            }
            return rev.substring(0, s.length() - j) + s;
        }
    }

    /**
     * kmp算法2
     */
    public static class Solution5 {
        public String shortestPalindrome(String s) {
            int[] pmt = getPmt(s);
            int len = s.length();
            int i = len - 1;
            int j = 0;
            while (i >= 0) {
                if (s.charAt(i) == s.charAt(j)) {
                    i--;
                    j++;
                } else if (j == 0) {
                    i--;
                } else {
                    j = pmt[j - 1];
                }
            }
            return new StringBuilder(s.substring(j)).reverse().append(s).toString();

        }

        private int[] getPmt(String s) {
            int len = s.length();
            int[] pmt = new int[len];
            int i = 1;
            int j = 0;
            while (i < len) {
                if (s.charAt(i) == s.charAt(j)) {
                    pmt[i++] = ++j;
                } else if (j == 0) {
                    i++;
                } else {
                    j = pmt[j - 1];
                }
            }
            return pmt;
        }
    }

    /**
     * kmp算法2,参考题解的另一种写法
     */
    public static class Solution6 {
        public String shortestPalindrome(String s) {
            int n = s.length();
            int[] fail = new int[n];
            Arrays.fill(fail, -1);
            for (int i = 1; i < n; i++) {
                int j = fail[i - 1];
                while (j != -1 && s.charAt(j + 1) != s.charAt(i)) {
                    j = fail[j];
                }
                if (s.charAt(j + 1) == s.charAt(i)) {
                    fail[i] = j + 1;
                }
            }
            int best = -1;
            for (int i = n - 1; i >= 0; i--) {
                while (best != -1 && s.charAt(best + 1) != s.charAt(i)) {
                    best = fail[best];
                }
                if (s.charAt(best + 1) == s.charAt(i)) {
                    best++;
                }
            }
            return new StringBuilder(s.substring(best + 1)).reverse().append(s).toString();
        }
    }
}
