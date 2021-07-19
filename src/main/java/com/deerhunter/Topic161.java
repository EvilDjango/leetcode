package com.deerhunter;

/**
 * 161. 相隔为 1 的编辑距离
 * 给定两个字符串 s 和 t，判断他们的编辑距离是否为 1。
 * <p>
 * 注意：
 * <p>
 * 满足编辑距离等于 1 有三种可能的情形：
 * <p>
 * 往 s 中插入一个字符得到 t
 * 从 s 中删除一个字符得到 t
 * 在 s 中替换一个字符得到 t
 * 示例 1：
 * <p>
 * 输入: s = "ab", t = "acb"
 * 输出: true
 * 解释: 可以将 'c' 插入字符串 s 来得到 t。
 * 示例 2:
 * <p>
 * 输入: s = "cab", t = "ad"
 * 输出: false
 * 解释: 无法通过 1 步操作使 s 变为 t。
 * 示例 3:
 * <p>
 * 输入: s = "1203", t = "1213"
 * 输出: true
 * 解释: 可以将字符串 s 中的 '0' 替换为 '1' 来得到 t。
 * 通过次数6,659提交次数19,908
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/16/21 6:14 PM
 */
public class Topic161 {
    public static class Solution1 {
        public boolean isOneEditDistance(String s, String t) {
            if (s.length() < t.length()) {
                String temp = s;
                s = t;
                t = temp;
            }
            int m = s.length();
            int n = t.length();
            if (m - n > 1) {
                return false;
            }
            boolean diff = false;
            int i = 0, j = 0;
            while (i < m) {
                if (j < n && s.charAt(i) == t.charAt(j)) {
                    i++;
                    j++;
                    continue;
                }
                if (diff) {
                    break;
                }
                if (m == n) {
                    i++;
                    j++;
                } else {
                    i++;
                }
                diff = true;

            }
            return diff && i == m;
        }
    }
}
