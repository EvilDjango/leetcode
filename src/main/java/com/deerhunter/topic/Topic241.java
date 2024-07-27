package com.deerhunter.topic;

import java.util.ArrayList;
import java.util.List;

/**
 * 241. 为运算表达式设计优先级
 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "2-1-1"
 * 输出: [0, 2]
 * 解释:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * 示例 2:
 * <p>
 * 输入: "2*3-4*5"
 * 输出: [-34, -14, -10, -10, 10]
 * 解释:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * 通过次数27,223提交次数36,956
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/6/21 11:15 AM
 */
public class Topic241 {
    public static class Solution1 {
        public List<Integer> diffWaysToCompute(String expression) {
            int len = expression.length();
            List<Integer> eles = new ArrayList<>();
            int i = 0;
            while (i < len) {
                int num = 0;
                while (i < len && expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                    num = num * 10 + expression.charAt(i) - '0';
                    i++;
                }
                eles.add(num);
                if (i < len) {
                    eles.add((int) expression.charAt(i++));
                }
            }
            return compute(eles, 0, eles.size());
        }

        private List<Integer> compute(List<Integer> eles, int left, int right) {
            List<Integer> result = new ArrayList<>();
            if (left + 1 == right) {
                result.add(eles.get(left));
                return result;
            }
            for (int i = left + 1; i < right; i += 2) {
                for (int num1 : compute(eles, left, i)) {
                    for (int num2 : compute(eles, i + 1, right)) {
                        result.add(calculate(num1, eles.get(i), num2));
                    }
                }
            }
            return result;
        }

        private int calculate(int i, int operator, int j) {
            switch (operator) {
                case '+':
                    return i + j;
                case '-':
                    return i - j;
                case '*':
                    return i * j;
                default:
                    throw new AssertionError();
            }
        }
    }

    /**
     * 对Solution1的优化，主要是移除了对效率提升不高的预处理
     */
    public static class Solution2 {
        public List<Integer> diffWaysToCompute(String expression) {
            return diffWaysToCompute(expression, 0, expression.length());
        }

        private List<Integer> diffWaysToCompute(String expression, int l, int r) {
            List<Integer> ret = new ArrayList<>();
            for (int i = l; i < r; i++) {
                char c = expression.charAt(i);
                if (c != '+' && c != '-' && c != '*') {
                    continue;
                }
                for (int left : diffWaysToCompute(expression, l, i)) {
                    for (int right : diffWaysToCompute(expression, i + 1, r)) {
                        switch (c) {
                            case '+':
                                ret.add(left + right);
                                break;
                            case '-':
                                ret.add(left - right);
                                break;
                            case '*':
                                ret.add(left * right);
                                break;
                        }
                    }
                }
            }
            if (ret.isEmpty()) {
                ret.add(Integer.valueOf(expression.substring(l, r)));
            }
            return ret;
        }
    }
}
