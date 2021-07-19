package com.deerhunter;

/**
 * 186. 翻转字符串里的单词 II
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 示例：
 * <p>
 * 输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * 输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * 注意：
 * <p>
 * 单词的定义是不包含空格的一系列字符
 * 输入字符串中不会包含前置或尾随的空格
 * 单词与单词之间永远是以单个空格隔开的
 * 进阶：使用 O(1) 额外空间复杂度的原地解法。
 * <p>
 * 通过次数6,816提交次数9,097
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/19/21 3:25 PM
 */
public class Topic186 {
    /**
     * 使用一个额外的数组
     */
    public static class Solution1 {
        public void reverseWords(char[] s) {
            int len = s.length;
            char[] temp = new char[len];
            int left = -1, right = len;
            for (int i = 0; i < len; i++) {
                if (s[i] == ' ') {
                    for (int j = i - 1; j > left; j--) {
                        temp[--right] = s[j];
                    }
                    temp[--right] = ' ';
                    left = i;
                }
            }
            System.arraycopy(s, left + 1, temp, 0, len - left - 1);
            System.arraycopy(temp, 0, s, 0, len);
        }
    }

    /**
     * 就地处理
     */
    public static class Solution2 {
        public void reverseWords(char[] s) {
            reverse(s, 0, s.length - 1);
            for (int i = 0, left = 0; i <= s.length; i++) {
                if (i == s.length || s[i] == ' ') {
                    reverse(s, left, i - 1);
                    left = i + 1;
                }
            }
        }

        private void reverse(char[] chars, int left, int right) {
            while (left < right) {
                char temp = chars[left];
                chars[left++] = chars[right];
                chars[right--] = temp;
            }
        }
    }
}
