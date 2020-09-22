package com.deerhunter;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * <p>
 * 返回符合要求的最少分割次数。
 * <p>
 * 示例:
 * <p>
 * 输入: "aab"
 * 输出: 1
 * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/21 15:49
 */
public class Topic132 {
    /**
     * 暴力回溯，会超时
     */
    public static class Solution1 {
        private int minCut;

        public int minCut(String s) {
            int len = s.length();
            if (len == 0) {
                return 0;
            }
            boolean[][] dp = new boolean[len][len];
            for (int i = 0; i < len; i++) {
                for (int j = 0; j <= i; j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[j][i] = i - j < 3 || dp[j + 1][i - 1];
                    }
                }
            }
            minCut = len - 1;
            dfs(s, 0, 0, dp);
            return minCut;
        }

        private void dfs(String s, int index, int cut, boolean[][] dp) {
            if (cut - 1 >= minCut) {
                return;
            }
            if (index == s.length()) {
                minCut = Math.min(minCut, cut - 1);
                return;
            }
            for (int i = index; i < s.length(); i++) {
                if (dp[index][i]) {
                    dfs(s, i + 1, cut + 1, dp);
                }
            }
        }
    }

    /**
     * 马拉车算法,依然超时
     */
    public static class Solution2 {
        private int minCut;

        public int minCut(String s) {
            int len = s.length();
            if (len == 0) {
                return 0;
            }
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
            }
            minCut = Integer.MAX_VALUE;
            dfs(0, p, len, 0);
            return minCut;
        }

        private void dfs(int i, int[] p, int len, int cut) {
            if (cut - 1 >= minCut) {
                return;
            }
            if (i == len) {
                minCut = Math.min(minCut, cut - 1);
                return;
            }
            for (int j = i; j < len; j++) {
                if (isPalindrome(p, i, j)) {
                    dfs(j + 1, p, len, cut + 1);
                }
            }
        }

        private boolean isPalindrome(int[] p, int left, int right) {
            return p[left + right + 2] - 1 >= right - left + 1;
        }
    }

    /**
     * 动态规划
     */
    public static class Solution3 {
        public int minCut(String s) {
            int len = s.length();
            if (len == 0) {
                return 0;
            }
            boolean[][] palindrome = new boolean[len][len];
            for (int i = 0; i < len; i++) {
                for (int j = 0; j <= i; j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        palindrome[j][i] = i - j < 3 || palindrome[j + 1][i - 1];
                    }
                }
            }
            int[] dp = new int[len];
            for (int i = 0; i < len; i++) {
                if (palindrome[0][i]) {
                    dp[i] = 0;
                    continue;
                }
                int minCut = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    if (palindrome[j + 1][i]) {
                        minCut = Math.min(minCut, dp[j] + 1);
                    }
                }
                dp[i] = minCut;
            }
            return dp[len - 1];
        }
    }

    /**
     * 动态规划+马拉车
     */
    public static class Solution4 {
        public int minCut(String s) {
            int len = s.length();
            if (len == 0) {
                return 0;
            }
            int newLen = 2 * len + 3;
            char[] chars = new char[newLen];
            chars[0] = '^';
            chars[1] = '#';
            int index = 1;
            for (int i = 0; i < len; i++) {
                chars[++index] = s.charAt(i);
                chars[++index] = '#';
            }
            chars[++index] = '$';
            int[] p = new int[newLen];
            int pivot = 2;
            int mx = 2;
            for (int i = 2; i < newLen - 2; i++) {
                if (i < mx) {
                    p[i] = Math.min(p[2 * pivot - i], mx - i);
                } else {
                    p[i] = 1;
                }
                while (chars[i - p[i]] == chars[i + p[i]]) {
                    p[i]++;
                }
                if (i + p[i] > mx) {
                    pivot = i;
                    mx = i + p[i];
                }
            }

            int[] dp = new int[len];
            for (int i = 0; i < len; i++) {
                if (isPalindrome(p, 0, i)) {
                    dp[i] = 0;
                    continue;
                }
                int minCut = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    if (isPalindrome(p, j + 1, i)) {
                        minCut = Math.min(minCut, dp[j] + 1);
                    }
                }
                dp[i] = minCut;
            }
            return dp[len - 1];
        }

        private boolean isPalindrome(int[] p, int left, int right) {
            return p[left + right + 2] - 1 >= right - left + 1;
        }
    }
}
