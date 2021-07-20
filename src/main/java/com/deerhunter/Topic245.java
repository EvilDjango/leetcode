package com.deerhunter;

/**
 * 245. 最短单词距离 III
 * 给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。
 * <p>
 * word1 和 word2 是有可能相同的，并且它们将分别表示为列表中两个独立的单词。
 * <p>
 * 示例:
 * 假设 words = ["practice", "makes", "perfect", "coding", "makes"].
 * <p>
 * 输入: word1 = “makes”, word2 = “coding”
 * 输出: 1
 * 输入: word1 = "makes", word2 = "makes"
 * 输出: 3
 * 注意:
 * 你可以假设 word1 和 word2 都在列表里。
 * <p>
 * 通过次数3,790提交次数6,136
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/20/21 2:02 PM
 */
public class Topic245 {
    public static class Solution1 {
        public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
            int ans = Integer.MAX_VALUE;
            int i1 = -1, i2 = -1;
            boolean same = word1.equals(word2);
            for (int i = 0; i < wordsDict.length; i++) {
                if (wordsDict[i].equals(word1)) {
                    if (same) {
                        i1 = i2;
                        i2 = i;
                    } else {
                        i1 = i;
                    }
                } else if (wordsDict[i].equals(word2)) {
                    i2 = i;
                }
                if (i1 != -1 && i2 != -1) {
                    ans = Math.min(ans, Math.abs(i2 - i1));
                }
            }
            return ans;
        }
    }

    /**
     * 参考题解写的一种简洁解法
     */
    public static class Solution2 {
        public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
            int ans = Integer.MAX_VALUE;
            int idx = -1;
            for (int i = 0; i < wordsDict.length; i++) {
                if (wordsDict[i].equals(word1) || wordsDict[i].equals(word2)) {
                    if (idx != -1 && (!wordsDict[i].equals(wordsDict[idx]) || word1.equals(word2))) {
                        ans = Math.min(ans, i - idx);
                    }
                    idx = i;
                }
            }
            return ans;
        }
    }
}
