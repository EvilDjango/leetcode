package com.deerhunter;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 * <p>
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-06
 */
public class Topic032 {
    public static int longestValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int maxPairs = 0;
        int tempLen = 0;
        int openCnt = 0;
        int closeCnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openCnt++;
            } else {
                closeCnt++;
            }
            if (closeCnt < openCnt) {
                tempLen++;
            } else if (closeCnt == openCnt) {
                tempLen++;
                maxPairs = Math.max(tempLen, maxPairs);
            } else {
                tempLen = 0;
                openCnt = 0;
                closeCnt = 0;
            }
        }

        tempLen = 0;
        openCnt = 0;
        closeCnt = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                openCnt++;
            } else {
                closeCnt++;
            }
            if (closeCnt > openCnt) {
                tempLen++;
            } else if (closeCnt == openCnt) {
                tempLen++;
                maxPairs = Math.max(tempLen, maxPairs);
            } else {
                tempLen = 0;
                openCnt = 0;
                closeCnt = 0;
            }
        }

        return maxPairs;
    }

    /**
     * 参考题解写的动态规划算法
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses2(String s) {
        // 第i个元素表示以字符串下标i结尾的字符串的最长有效长度
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                continue;
            }
            if (s.charAt(i - 1) == '(') {
                dp[i] = i - 2 >= 0 ? dp[i - 2] + 2 : 2;
            } else {
                int index = i - dp[i - 1] - 1;
                if (index >= 0 && s.charAt(index) == '(') {
                    dp[i] = dp[i - 1] + (index - 1 >= 0 ? dp[index - 1] : 0) + 2;
                }
            }
        }
        int maxLen = 0;
        for (int len : dp) {
            maxLen = Math.max(len, maxLen);
        }
        return maxLen;
    }
}
