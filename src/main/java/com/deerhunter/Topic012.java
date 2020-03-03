package com.deerhunter;

/**
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-to-roman
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-28
 */
public class Topic012 {
    private char[] singles = {'I', 'X', 'C', 'M'};
    private char[] fives = {'V', 'L', 'D'};

    public String intToRoman(int num) {
        String ret = "";
        int bit = 0;
        while (num != 0) {
            ret = getRoman(bit, num % 10) + ret;
            num /= 10;
            bit++;
        }
        return ret;
    }

    private String getRoman(int bit, int num) {
        char single = singles[bit];
        if (num < 4) {
            return repeat(single, num);
        }

        if (num == 4) {
            return "" + single + fives[bit];
        }
        if (num == 9) {
            return "" + single + singles[bit + 1];
        }
        return "" + fives[bit] + repeat(single, num - 5);
    }

    private String repeat(char c, int count) {
        String ret = "";
        for (int i = 0; i < count; i++) {
            ret += c;
        }
        return ret;
    }
}
