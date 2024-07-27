package com.deerhunter.topic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 验证给定的字符串是否可以解释为十进制数字。
 * <p>
 * 例如:
 * <p>
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 * <p>
 * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
 * <p>
 * 数字 0-9
 * 指数 - "e"
 * 正/负号 - "+"/"-"
 * 小数点 - "."
 * 当然，在输入中，这些字符的上下文也很重要。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-18
 */
public class Topic065 {
    /**
     * 正则表达式。LeetCode不允许这种解法
     */
    public static class Solution1 {
        public boolean isNumber(String s) {
            return Pattern.matches("\\s*[+-]?(\\d+\\.?\\d*|\\d*\\.?\\d+)(e[+-]?\\d+)?\\s*", s);
        }
    }


    /**
     * 普通的算法
     */
    public static class Solution2 {
        public boolean isNumber(String s) {
            s = s.trim();
            int eCount = 0;
            int pointCount = 0;
            int numCount = 0;
            char prev = ' ';
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    numCount++;
                } else if (c == 'e') {
                    // 如果e前面没有数字，无效
                    if (numCount == 0) {
                        return false;
                    }
                    eCount++;
                    if (eCount > 1) {
                        return false;
                    }
                    numCount = 0;
                    // 出现了e之后，不允许再出现小数点，所以这里把小数点数量增加到最大值
                    pointCount = 1;
                } else if (c == '.') {
                    pointCount++;
                    if (pointCount > 1) {
                        return false;
                    }
                } else if (c == '+' || c == '-') {
                    // 符号只能出现在有效字符（除空白外的有效字符）的开头或者e之后
                    if (i != 0 && prev != 'e') {
                        return false;
                    }
                } else {
                    // 出现其他非法字符串，无效
                    return false;
                }
                prev = c;
            }
            return numCount > 0;
        }
    }

    /**
     * 表驱动法，参考 https://leetcode-cn.com/problems/valid-number/solution/biao-qu-dong-fa-by-user8973/
     * 状态转移表：
     * <p>
     * | state | blank | +/-  | 0-9  | .    | e    | other |
     * | ----- | ----- | ---- | ---- | ---- | ---- | ----- |
     * | 0     | 0     | 1    | 2    | 3    | -1   | -1    |
     * | 1     | -1    | -1   | 2    | 3    | -1   | -1    |
     * | 2     | 8     | -1   | 2    | 4    | 5    | -1    |
     * | 3     | -1    | -1   | 4    | -1   | -1   | -1    |
     * | 4     | 8     | -1   | 4    | -1   | 5    | -1    |
     * | 5     | -1    | 6    | 7    | -1   | -1   | -1    |
     * | 6     | -1    | -1   | 7    | -1   | -1   | -1    |
     * | 7     | 8     | -1   | 7    | -1   | -1   | -1    |
     * | 8     | 8     | -1   | -1   | -1   | -1   | -1    |
     */
    public static class Solution3 {
        public boolean isNumber(String s) {
            int[][] transfer = {
                    {0, 1, 2, 3, -1, -1},
                    {-1, -1, 2, 3, -1, -1},
                    {8, -1, 2, 4, 5, -1},
                    {-1, -1, 4, -1, -1, -1},
                    {8, -1, 4, -1, 5, -1},
                    {-1, 6, 7, -1, -1, -1},
                    {-1, -1, 7, -1, -1, -1},
                    {8, -1, 7, -1, -1, -1},
                    {8, -1, -1, -1, -1, -1, -1}
            };

            int state = 0;
            for (int i = 0; i < s.length(); i++) {
                int index = getIndex(s.charAt(i));
                state = transfer[state][index];
                if (state < 0) {
                    return false;
                }
            }
            int finals = 0b110010100;
            return (1 << state & finals) > 0;

        }

        /**
         * 获取每种字符对应的编号
         *
         * @param c
         * @return
         */
        private int getIndex(char c) {
            switch (c) {
                case ' ':
                    return 0;
                case '+':
                    // pass through
                case '-':
                    return 1;
                case '.':
                    return 3;
                case 'e':
                    return 4;
                default:
                    if (Character.isDigit(c)) {
                        return 2;
                    } else {
                        return 5;
                    }
            }
        }
    }
}
