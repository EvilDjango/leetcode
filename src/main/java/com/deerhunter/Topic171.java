package com.deerhunter;

/**
 * 171. Excel表列序号
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 * <p>
 * 例如，
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * 示例 1:
 * <p>
 * 输入: "A"
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: "AB"
 * 输出: 28
 * 示例 3:
 * <p>
 * 输入: "ZY"
 * 输出: 701
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/12/25 12:24
 */
public class Topic171 {
    public static class Solution {
        public int titleToNumber(String s) {
            int res = 0;
            int base = 1;
            for (int i = s.length() - 1; i >= 0; i--) {
                res += base * (s.charAt(i) - 'A' + 1);
                base *= 26;
            }
            return res;
        }
    }
}
