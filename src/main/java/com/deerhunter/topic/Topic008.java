package com.deerhunter.topic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-14
 */
public class Topic008 {
    public int myAtoi(String str) {
        if (null == str) {
            return 0;
        }
        boolean foundNum = false;
        boolean negative = false;
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 查找数字开端
            if (!foundNum) {
                if (c == 32) {
                    // 跳过空字符
                    continue;
                } else if (c == '-' || c == '+') {
                    negative = '-' == c;
                    foundNum = true;
                } else if (Character.isDigit(c)) {
                    num = Character.digit(c, 10);
                    foundNum = true;
                } else {
                    // 第一个非空字符串非数字或+-符号，认为是非法字符串
                    return 0;
                }
                // 查找连续的数字
            } else if (Character.isDigit(c)) {
                int digit = Character.digit(c, 10);
                if (negative) {
                    digit = -digit;
                }

                if (num > Integer.MAX_VALUE / 10 || (num == Integer.MAX_VALUE / 10 && digit > 7)) {
                    return Integer.MAX_VALUE;
                }
                if (num < Integer.MIN_VALUE / 10 || (num == Integer.MIN_VALUE / 10 && digit < -8)) {
                    return Integer.MIN_VALUE;
                }
                num = 10 * num + digit;

                // 在数字开端之后，遇到非数字字符，终结
            } else {
                break;
            }
        }
        return num;
    }

    public int myAtoi2(String str) {
        if (null == str) {
            return 0;
        }
        Pattern pattern = Pattern.compile("\\s*([+-]?\\d+).*");
        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches()) {
            return 0;
        }
        String numStr = matcher.group(1);
        char first = numStr.charAt(0);
        boolean negative = false;
        if (first == '+' || first == '-') {
            negative = first == '-';
            numStr = numStr.substring(1);
        }
        int num = 0;
        for (int i = 0; i < numStr.length(); i++) {
            int digit = Character.digit(numStr.charAt(i), 10);
            if (negative) {
                digit = -digit;
            }

            if (num > Integer.MAX_VALUE / 10 || (num == Integer.MAX_VALUE / 10 && digit > 7)) {
                return Integer.MAX_VALUE;
            }
            if (num < Integer.MIN_VALUE / 10 || (num == Integer.MIN_VALUE / 10 && digit < -8)) {
                return Integer.MIN_VALUE;
            }
            num = 10 * num + digit;
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }

}
