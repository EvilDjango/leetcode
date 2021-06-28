package com.deerhunter;

/**
 * 227. 基本计算器 II
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * <p>
 * 整数除法仅保留整数部分。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "3+2*2"
 * 输出：7
 * 示例 2：
 * <p>
 * 输入：s = " 3/2 "
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 3 * 105
 * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 * s 表示一个 有效表达式
 * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
 * 题目数据保证答案是一个 32-bit 整数
 * 通过次数74,693提交次数171,766
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 6/28/21 4:39 PM
 */
public class Topic227 {
    public static class Solution1 {
        public int calculate(String s) {
            int ans = 0;
            Integer temp = null;
            char symbol = ' ';
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ' ') {
                    continue;
                }
                if (!Character.isDigit(c)) {
                    symbol = c;
                    continue;
                }

                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                if (temp == null) {
                    temp = num;
                    continue;
                }

                if (symbol == '-') {
                    num = -num;
                }
                switch (symbol) {
                    case ' ':
                    case '+':
                    case '-':
                        ans += temp;
                        temp = num;
                        break;
                    case '*':
                        temp *= num;
                        break;
                    case '/':
                        temp /= num;
                        break;
                }
                symbol = ' ';
            }
            return temp == null ? ans : ans + temp;
        }
    }
}
