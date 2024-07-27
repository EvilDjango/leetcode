package com.deerhunter.topic;

import com.deerhunter.common.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 * <p>
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 * <p>
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-26
 */
public class Topic072 {
    /**
     * 自己写的算法，算法思路有问题，仅在某些情况下能得到正确结果
     * <p>
     * 但是写了一天，还是留下做个纪念吧。
     */
    public static class Solution1 {
        public int minDistance(String word1, String word2) {
            if (Objects.equals(word1, word2)) {
                return 0;
            }

            if (word2.length() == 0) {
                return word1.length();
            }


            Map<Character, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < word1.length(); i++) {
                char c = word1.charAt(i);
                map.putIfAbsent(c, new ArrayList<>());
                map.get(c).add(i);
            }

            int len = word2.length();
            int ans = Integer.MAX_VALUE;

            for (int startIndex = 0; startIndex < len; ) {
                int[] word1Indexes = new int[len];
                Arrays.fill(word1Indexes, -1);
                int firstMatch = -1;
                int matchCount = 0;
                int prevMatch = -1;
                for (int j = startIndex; j < len; j++) {
                    int matchIndex = -1;
                    List<Integer> matchIndexes = map.get(word2.charAt(j));
                    if (matchIndexes != null) {
                        for (int index : matchIndexes) {
                            // word1中匹配到的字符，应该与word2中的字符顺序一致
                            if (index > prevMatch) {
                                matchIndex = index;
                                break;
                            }
                        }
                    }
                    if (matchIndex != -1) {
                        if (firstMatch == -1) {
                            firstMatch = j;
                        }
                        matchCount++;
                        prevMatch = matchIndex;
                    }

                    word1Indexes[j] = matchIndex;
                }

                int left = -1;
                for (int right = 0; right <= len; right++) {
                    if (right == len || word1Indexes[right] != -1) {
                        if (right - left > 1) {
                            int left1 = left < 0 ? -1 : word1Indexes[left];
                            int right1 = right == len ? word1.length() : word1Indexes[right];
                            int space = right1 - left1 - 1;
                            for (int j = left + 1; j < right; j++) {
                                word1Indexes[j] = j - left <= space ? -1 : -2;
                            }
                        }
                        left = right;
                    }
                }
                ans = Math.min(ans, calDistance(word1, word1Indexes));
                // 如果完全匹配或者无匹配，则已经得到了最优解
                if (firstMatch == -1 || matchCount == len - firstMatch + 1) {
                    return ans;
                }
                startIndex = firstMatch + 1;
            }
            return ans;
        }

        private int calDistance(String word1, int[] word1Indexes) {
            int insertCount = 0;
            int replaceCount = 0;
            for (int index : word1Indexes) {
                if (index == -2) {
                    insertCount++;
                } else if (index == -1) {
                    replaceCount++;
                }
            }
            int deleteCount = word1.length() - (word1Indexes.length - insertCount);
            return deleteCount + replaceCount + insertCount;
        }
    }

    /**
     * 动态规划解法
     */
    public static class Solution2 {
        public int minDistance(String word1, String word2) {
            int M = word1.length();
            int N = word2.length();
            if (M * N == 0) {
                return M + N;
            }

            int[][] dp = new int[M + 1][N + 1];
            for (int i = 0; i < M + 1; i++) {
                dp[i][0] = i;
            }
            for (int i = 0; i < N + 1; i++) {
                dp[0][i] = i;
            }
            for (int i = 1; i < M + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    int left = dp[i - 1][j] + 1;
                    int top = dp[i][j - 1] + 1;
                    int topLeft = word1.charAt(i - 1) == word2.charAt(j - 1) ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(Math.min(left, top), topLeft);
                }
            }
            return dp[M][N];
        }
    }
}
