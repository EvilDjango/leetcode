package com.deerhunter;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 017. 含有所有字符的最短字符串
 * 给定两个字符串 s 和 t 。返回 s 中包含 t 的所有字符的最短子字符串。如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。
 * <p>
 * 如果 s 中存在多个符合条件的子字符串，返回任意一个。
 * <p>
 * <p>
 * <p>
 * 注意： 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最短子字符串 "BANC" 包含了字符串 t 的所有字符 'A'、'B'、'C'
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3：
 * <p>
 * 输入：s = "a", t = "aa"
 * 输出：""
 * 解释：t 中两个字符 'a' 均应包含在 s 的子串中，因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 * <p>
 * <p>
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 * <p>
 * <p>
 * <p>
 * 注意：本题与主站 76 题相似（本题答案不唯一）：https://leetcode-cn.com/problems/minimum-window-substring/
 * <p>
 * 通过次数12,695提交次数25,289
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/20 下午10:29
 */
public class M1oyTv {
    /**
     * 参考了优秀题解
     */
    public String minWindow(String s, String t) {
        int tLen = t.length();
        int[] charCount = new int[128];
        for (char c : t.toCharArray()) {
            charCount[c]--;
        }
        int l = 0, cnt = 0;
        int left = 0, right = 0, min = Integer.MAX_VALUE;
        char[] chars = s.toCharArray();
        for (int r = 0; r < s.length(); r++) {
            charCount[chars[r]]++;
            if (charCount[chars[r]] <= 0) {
                cnt++;
            }
            while (cnt == tLen && charCount[chars[l]] > 0) {
                charCount[chars[l]]--;
                l++;
            }
            if (cnt == tLen && min > r - l + 1) {
                min = r - l + 1;
                left = l;
                right = r;
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(left, right + 1);
    }
}
