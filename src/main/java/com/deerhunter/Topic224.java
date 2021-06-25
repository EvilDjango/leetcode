package com.deerhunter;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 224. 基本计算器
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * 通过次数62,114提交次数149,346
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 6/24/21 5:21 PM
 */
public class Topic224 {
    /**
     * 递归
     */
    public static class Solution1 {
        public int calculate(String s) {
            return calculate(s, 0, s.length(), true);
        }

        private int calculate(String s, int l, int r, boolean positive) {
            if (l == r) {
                return 0;
            }
            char c = s.charAt(l);
            if (c == ' ' || c == '+') {
                return calculate(s, l + 1, r, positive);
            }
            if (c == '-') {
                return calculate(s, l + 1, r, false);
            }

            if (c == '(') {
                int brackets = 1;
                int i = l + 1;
                while (brackets > 0) {
                    char start = s.charAt(i);
                    if (start == '(') {
                        brackets++;
                    } else if (start == ')') {
                        brackets--;
                    }
                    i++;
                }
                int next = calculate(s, l + 1, i - 1, true);
                return calculate(s, i, r, true) + (positive ? next : -next);
            }


            int num = 0;
            while (l < r && s.charAt(l) >= '0' && s.charAt(l) <= '9') {
                num = num * 10 + s.charAt(l) - '0';
                l++;
            }
            return calculate(s, l, r, true) + (positive ? num : -num);
        }
    }

    /**
     * 循环
     */
    public static class Solution2 {
        public int calculate(String s) {
            int len = s.length();
            // 保存每个层级的操作数
            Deque<Integer> ops = new ArrayDeque<>();
            // 符号
            Deque<Integer> signs = new ArrayDeque<>();
            ops.push(0);
            signs.push(1);
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                if (c == ' ' || c == '+') {
                    continue;
                }
                if (c == '-') {
                    signs.pop();
                    signs.push(-1);
                    continue;
                }
                if (c == '(') {
                    ops.push(0);
                    signs.push(1);
                    continue;
                }

                int num;
                if (c == ')') {
                    num = ops.pop();
                    signs.pop();
                } else {
                    num = 0;
                    while (i < len && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                        num = num * 10 + s.charAt(i) - '0';
                        i++;
                    }
                    i--;
                }
                int sign = signs.peek();
                ops.push(ops.pop() + num * sign);
                // 负号用掉后复位
                if (sign == -1) {
                    signs.pop();
                    signs.push(1);
                }
            }
            return ops.pop();
        }
    }

    /**
     * 循环
     */
    public static class Solution3 {
        public int calculate(String s) {
            int ans = 0;
            Deque<Integer> ops = new ArrayDeque<>();
            ops.push(1);
            int sign = 1;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ' ') {
                    continue;
                }
                if (c == '+') {
                    sign = ops.peek();
                    continue;
                }
                if (c == '-') {
                    sign = -1 * ops.peek();
                    continue;
                }
                if (c == '(') {
                    ops.push(sign);
                    continue;
                }
                if (c == ')') {
                    ops.pop();
                    continue;
                }

                int num = 0;
                while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                ans += sign * num;
            }
            return ans;
        }
    }
}
