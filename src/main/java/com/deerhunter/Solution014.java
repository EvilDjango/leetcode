package com.deerhunter;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-28
 */
public class Solution014 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        String shortestPrefix = commonPrefix(strs[0], strs[strs.length - 1]);
        if (shortestPrefix.isEmpty()) {
            return "";
        }
        String secondShortPrefix = shortestPrefix;
        int left = 1;
        int right = strs.length - 2;
        while (left <= right) {
            String leftS = strs[left];
            String rightS = strs[right];
            String commonPrefix = left == right ? commonPrefix(shortestPrefix, leftS) : commonPrefix(leftS, rightS);
            if (commonPrefix.isEmpty()) {
                return "";
            }
            left++;
            right--;
            if (commonPrefix.length() < shortestPrefix.length()) {
                shortestPrefix = commonPrefix;
                continue;
            }
            if (commonPrefix.length() < secondShortPrefix.length()) {
                secondShortPrefix = commonPrefix;
            }
        }
        return commonPrefix(shortestPrefix, secondShortPrefix);
    }

    private String commonPrefix(String s1, String s2) {
        String commonPrefix = "";
        for (int i = 0; i < s1.length() && i < s2.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                break;
            }
            commonPrefix += s1.charAt(i);
        }
        return commonPrefix;
    }

}