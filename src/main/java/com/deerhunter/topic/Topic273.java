package com.deerhunter.topic;

/**
 * 273. 整数转换英文表示
 * 将非负整数 num 转换为其对应的英文表示。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：num = 123
 * 输出："One Hundred Twenty Three"
 * 示例 2：
 * <p>
 * 输入：num = 12345
 * 输出："Twelve Thousand Three Hundred Forty Five"
 * 示例 3：
 * <p>
 * 输入：num = 1234567
 * 输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * 示例 4：
 * <p>
 * 输入：num = 1234567891
 * 输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= num <= 231 - 1
 * 通过次数11,165提交次数36,368
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/12/21 12:26 PM
 */
public class Topic273 {
    public static class Solution1 {
        private String[] base = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
                "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"};
        private String[] base10 = {"", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        private String[] units = {"", "Thousand", "Million", "Billion"};

        public String numberToWords(int num) {
            if (num == 0) {
                return "Zero";
            }
            String ret = "";
            for (int i = 0; num > 0 && i < 4; i++) {
                String digit = tripleDigitToWord(num % 1000);
                if (!digit.isEmpty()) {
                    ret = digit + units[i] + ret;
                }
                num /= 1000;
            }
            return addBlank(ret);
        }

        private String addBlank(String s) {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (c >= 'A' && c <= 'Z') {
                    sb.append(' ');
                }
                sb.append(c);
            }
            sb.delete(0, 1);
            return sb.toString();
        }

        private String tripleDigitToWord(int num) {
            String word = "";
            if (num >= 100) {
                word += base[num / 100 - 1] + "Hundred";
                num %= 100;
            }
            if (num > 20) {
                word += base10[num / 10 - 1];
                num %= 10;
            }
            if (num > 0) {
                word += base[num - 1];
            }
            return word;
        }
    }
}
