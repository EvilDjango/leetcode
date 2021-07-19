package com.deerhunter;

/**
 * 243. 最短单词距离
 * 给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。
 * <p>
 * 示例:
 * 假设 words = ["practice", "makes", "perfect", "coding", "makes"]
 * <p>
 * 输入: word1 = “coding”, word2 = “practice”
 * 输出: 3
 * 输入: word1 = "makes", word2 = "coding"
 * 输出: 1
 * 注意:
 * 你可以假设 word1 不等于 word2, 并且 word1 和 word2 都在列表里。
 * <p>
 * 通过次数7,816提交次数11,937
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/19/21 5:01 PM
 */
public class Topic243 {
    public static class Solution1 {
        public int shortestDistance(String[] wordsDict, String word1, String word2) {
            int i = -1, j = -1, ans = Integer.MAX_VALUE;
            for (int k = 0; k < wordsDict.length; k++) {
                if (word1.equals(wordsDict[k])) {
                    if (j >= 0) {
                        ans = Math.min(ans, k - j);
                    }
                    i = k;
                } else if (word2.equals(wordsDict[k])) {
                    if (i >= 0) {
                        ans = Math.min(ans, k - i);
                    }
                    j = k;
                }
            }
            return ans;
        }
    }
}
