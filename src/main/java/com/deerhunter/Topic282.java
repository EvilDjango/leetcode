package com.deerhunter;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. 给表达式添加运算符
 * 给定一个仅包含数字 0-9 的字符串和一个目标值，在数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回所有能够得到目标值的表达式。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = "123", target = 6
 * 输出: ["1+2+3", "1*2*3"]
 * 示例 2:
 * <p>
 * 输入: num = "232", target = 8
 * 输出: ["2*3+2", "2+3*2"]
 * 示例 3:
 * <p>
 * 输入: num = "105", target = 5
 * 输出: ["1*0+5","10-5"]
 * 示例 4:
 * <p>
 * 输入: num = "00", target = 0
 * 输出: ["0+0", "0-0", "0*0"]
 * 示例 5:
 * <p>
 * 输入: num = "3456237490", target = 9191
 * 输出: []
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= num.length <= 10
 * num 仅含数字
 * 通过次数6,465提交次数16,765
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/14/21 11:36 AM
 */
public class Topic282 {
    public static class Solution1 {
        public List<String> addOperators(String num, int target) {
            List<String> ret = new ArrayList<>();
            addOperators(num, target, 0, new StringBuilder(), ret, true, true);
            return ret;
        }

        private void addOperators(String expr, int target, int index, StringBuilder sb, List<String> list, boolean prevIsSymbol, boolean allowNumber) {
            if (index == expr.length()) {
                if (eval(sb) == target) {
                    list.add(sb.toString());
                }
                return;
            }
            int len = sb.length();
            if (allowNumber) {
                char cur = expr.charAt(index);
                sb.append(cur);
                addOperators(expr, target, index + 1, sb, list, false, !prevIsSymbol || cur != '0');
                sb.delete(len, len + 1);
            }

            if (prevIsSymbol) {
                return;
            }
            for (char c : new char[]{'+', '-', '*'}) {
                sb.append(c);
                addOperators(expr, target, index, sb, list, true, true);
                sb.delete(len, len + 1);
            }
        }

        private long eval(StringBuilder exp) {
            int len = exp.length();
            int i = 0;
            long prev = 0;
            long cur = 0;
            while (i < len) {
                char c = exp.charAt(i);
                if (c == '-' || c == '+' || c == '*') {
                    i++;
                }
                long num = 0;
                while (i < len && exp.charAt(i) >= '0' && exp.charAt(i) <= '9') {
                    num = num * 10 + exp.charAt(i) - '0';
                    i++;
                }

                if (c == '*') {
                    cur *= num;
                } else if (c == '-') {
                    prev += cur;
                    cur = -num;
                } else {
                    prev += cur;
                    cur = num;
                }
            }
            return prev + cur;
        }
    }

    /**
     * 参考优秀题解
     */
    public static class Solution2 {
        public List<String> addOperators(String num, int target) {
            List<String> ans = new ArrayList<>();
            if (num == null || num.length() == 0) {
                return ans;
            }
            helper(num, target, 0, "", 0, 0, ans);
            return ans;
        }

        private void helper(String num, int target, int pos, String path, long value, long prevOperand, List<String> ans) {
            if (pos == num.length()) {
                if (value == target) {
                    ans.add(path);
                }
                return;
            }
            for (int i = pos; i < num.length(); i++) {
                // 以0开头的多位数字非法
                if (num.charAt(pos) == '0' && i != pos) {
                    break;
                }
                long cur = Long.parseLong(num.substring(pos, i + 1));
                if (pos == 0) {
                    helper(num, target, i + 1, "" + cur, cur, cur, ans);
                } else {
                    helper(num, target, i + 1, path + "+" + cur, value + cur, cur, ans);
                    helper(num, target, i + 1, path + "-" + cur, value - cur, -cur, ans);
                    helper(num, target, i + 1, path + "*" + cur, value - prevOperand + cur * prevOperand, cur * prevOperand, ans);
                }
            }
        }
    }

}
