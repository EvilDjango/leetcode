package com.deerhunter.offer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * s.length <= 40000
 * 注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/28 下午12:12
 */
public class Offer048 {
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        HashSet<Character> set = new HashSet<>();
        int left = 0, right = 0;
        int maxLen = 0;
        while (right < n) {
            while (set.contains(chars[right])) {
                set.remove(chars[left++]);
            }
            set.add(chars[right++]);
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }

    /**
     * 参考最快的提交记录。通过利用前一位置的最大无重复序列来加快运算。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        Map<Character, Integer> lastSeen = new HashMap<>();
        int maxLen = 0;
        // 以前一位置结尾的最长无重复字符串
        int pre = 0;
        for (int right = 0; right < n; right++) {
            int cur = Math.min(right - lastSeen.getOrDefault(chars[right], -1), pre + 1);
            pre = cur;
            maxLen = Math.max(maxLen, cur);
            lastSeen.put(chars[right], right);
        }
        return maxLen;
    }
}
