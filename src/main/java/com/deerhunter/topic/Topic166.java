package com.deerhunter.topic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 166. 分数到小数
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
 * <p>
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * <p>
 * 如果存在多个答案，只需返回 任意一个 。
 * <p>
 * 对于所有给定的输入，保证 答案字符串的长度小于 104 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：numerator = 1, denominator = 2
 * 输出："0.5"
 * 示例 2：
 * <p>
 * 输入：numerator = 2, denominator = 1
 * 输出："2"
 * 示例 3：
 * <p>
 * 输入：numerator = 2, denominator = 3
 * 输出："0.(6)"
 * 示例 4：
 * <p>
 * 输入：numerator = 4, denominator = 333
 * 输出："0.(012)"
 * 示例 5：
 * <p>
 * 输入：numerator = 1, denominator = 5
 * 输出："0.2"
 * <p>
 * <p>
 * 提示：
 * <p>
 * -231 <= numerator, denominator <= 231 - 1
 * denominator != 0
 * 通过次数17,960提交次数63,740
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/12/23 09:59
 */
public class Topic166 {
    /**
     * 使用BigDecimal，会超时
     */
    public static class Solution1 {
        public String fractionToDecimal(int numerator, int denominator) {
            int scale = 10_000;
            BigDecimal quotient = BigDecimal.valueOf(numerator).divide(BigDecimal.valueOf(denominator), scale, RoundingMode.DOWN)
                    .stripTrailingZeros();

            // 无整数部分直接返回准确值
            if (quotient.scale() <= 0) {
                return quotient.setScale(0, RoundingMode.UNNECESSARY).toString();
            }

            String quotientStr = quotient.toString();

            int point = quotientStr.indexOf('.');
            String integer = quotientStr.substring(0, point);
            String decimal = processDecimal(quotientStr.substring(point + 1));
            return integer + "." + decimal;
        }

        private String processDecimal(String decimal) {
            Map<Character, List<Integer>> visited = new HashMap<>();
            String pattern;
            for (int i = 0; i < decimal.length(); i++) {
                char c = decimal.charAt(i);
                if (visited.containsKey(c)) {
                    List<Integer> lastPositions = visited.get(c);
                    for (int position : lastPositions) {
                        pattern = decimal.substring(position, i);
                        if (isRepeatPattern(decimal.substring(position), pattern)) {
                            return decimal.substring(0, position) + "(" + pattern + ")";
                        }
                    }
                }
                visited.computeIfAbsent(c, ArrayList::new);
                visited.get(c).add(i);
            }
            return decimal;
        }

        private boolean isRepeatPattern(String s, String pattern) {
            while (s.length() > pattern.length() && s.startsWith(pattern)) {
                s = s.substring(pattern.length());
            }
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != pattern.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 长除法
     */
    public static class Solution2 {
        public String fractionToDecimal(int numerator, int denominator) {
            if (numerator == 0) {
                return "0";
            }
            StringBuilder sb = new StringBuilder();
            if (numerator < 0 ^ denominator < 0) {
                sb.append('-');
            }
            long dividend = Math.abs((long)numerator);
            long divisor = Math.abs((long)denominator);
            sb.append(dividend / divisor);
            long remainder = (int) (dividend % divisor);
            if (remainder == 0) {
                return sb.toString();
            }
            sb.append('.');
            Map<Long, Integer> remainderPosition = new HashMap<>();
            while (remainder != 0) {
                if (remainderPosition.containsKey(remainder)) {
                    sb.insert(remainderPosition.get(remainder), "(");
                    sb.append(')');
                    break;
                }
                remainderPosition.put(remainder, sb.length());
                remainder *= 10;
                sb.append(remainder / divisor);
                remainder %= divisor;
            }
            return sb.toString();
        }
    }
}
