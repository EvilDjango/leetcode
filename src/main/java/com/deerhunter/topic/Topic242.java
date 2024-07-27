package com.deerhunter.topic;

import java.util.HashMap;
import java.util.Map;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * <p>
 * 通过次数251,516提交次数392,717
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/6/21 2:49 PM
 */
public class Topic242 {
    public static class Solution1 {
        public boolean isAnagram(String s, String t) {
            Map<Character, Integer> count = new HashMap<>();
            for (char c : s.toCharArray()) {
                count.merge(c, 1, Integer::sum);
            }
            for (char c : t.toCharArray()) {
                if (!count.containsKey(c)) {
                    return false;
                }
                if (count.get(c) == 1) {
                    count.remove(c);
                } else {
                    count.put(c, count.get(c) - 1);
                }
            }
            return count.isEmpty();
        }
    }
}
