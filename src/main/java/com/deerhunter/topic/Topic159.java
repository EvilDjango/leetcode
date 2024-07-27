package com.deerhunter.topic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 159. 至多包含两个不同字符的最长子串
 * 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "eceba"
 * 输出: 3
 * 解释: t 是 "ece"，长度为3。
 * 示例 2:
 * <p>
 * 输入: "ccaabbb"
 * 输出: 5
 * 解释: t 是 "aabbb"，长度为5。
 * 通过次数12,907提交次数24,209
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/15/21 4:04 PM
 */
public class Topic159 {
    /**
     * 滑动窗口
     */
    public static class Solution1 {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            int ret = 0;
            int l = 0, r = 0, variety = 0;
            Character c1 = null, c2 = null;
            while (r < s.length()) {
                Character c = s.charAt(r++);
                if (c.equals(c1) || c.equals(c2)) {
                    continue;
                }
                c1 = c2;
                c2 = c;
                variety++;
                if (variety <= 2) {
                    continue;
                }
                ret = Math.max(ret, r - l - 1);
                l = r - 2;
                c2 = s.charAt(r - 1);
                c1 = s.charAt(l);
                while (s.charAt(l - 1) == c1) {
                    l--;
                }
                variety--;
            }
            ret = Math.max(ret, r - l);
            return ret;
        }
    }

    /**
     * 使用哈希表辅助的滑动窗口
     */
    public static class Solution2 {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            int ret = 0;
            int left = 0, right = 0;
            Map<Character, Integer> map = new HashMap<>();
            while (right < s.length()) {
                map.put(s.charAt(right), right++);
                if (map.size() > 2) {
                    int toAbandon = Collections.min(map.values());
                    map.remove(s.charAt(toAbandon));
                    left = toAbandon + 1;
                }
                ret = Math.max(ret, right - left);
            }
            return ret;
        }
    }
}
