package com.deerhunter;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/16 16:16
 */
public class Topic125 {
    public static class Solution {
        public static boolean isPalindrome(String s) {
            int len = s.length();
            int left = 0, right = len - 1;
            while (left < right) {
                while (left < right && !isAlphabetOrDigit(s.charAt(left))) {
                    left++;
                }
                while (left < right && !isAlphabetOrDigit(s.charAt(right))) {
                    right--;
                }
                if (left < right) {
                    if (s.charAt(left) != s.charAt(right) && !isSameLetterInDifferentCase(s.charAt(left), s.charAt(right))) {
                        return false;
                    }
                }

                left++;
                right--;
            }
            return true;
        }

        private static boolean isSameLetterInDifferentCase(char left, char right) {
            if (left > right) {
                char temp = left;
                left = right;
                right = temp;
            }
            return left >= 'A' && right <= 'z' && right - left == 32;
        }

        private static boolean isAlphabetOrDigit(char c) {
            return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
        }

    }
}
