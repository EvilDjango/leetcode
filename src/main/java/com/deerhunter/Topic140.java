package com.deerhunter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * <p>
 * 说明：
 * <p>
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * 示例 2：
 * <p>
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/10/23 17:20
 */
public class Topic140 {
    /**
     * 暴力算法，会超时
     */
    public static class Solution1 {
        public List<String> wordBreak(String s, List<String> wordDict) {
            List<String> result = new ArrayList<>();
            dfs(s, wordDict, result, new ArrayList<>(), 0);
            return result;
        }

        private void dfs(String s, List<String> wordDict, List<String> result, List<String> words, int index) {
            if (index == s.length()) {
                StringBuilder sb = new StringBuilder();
                for (String word : words) {
                    sb.append(word).append(" ");
                }
                sb.delete(sb.length() - 1, sb.length());
                result.add(sb.toString());
                return;
            }
            for (String word : wordDict) {
                if (s.startsWith(word, index)) {
                    words.add(word);
                    dfs(s, wordDict, result, words, index + word.length());
                    words.remove(words.size() - 1);
                }
            }
        }
    }

    public static class Solution2 {
        public List<String> wordBreak(String s, List<String> wordDict) {
            Map<Integer, List<List<String>>> map = new HashMap<>();
            List<List<String>> list = dfs(s, map, wordDict, 0);
            List<String> result = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (List<String> words : list) {
                for (String word : words) {
                    sb.append(word).append(" ");
                }
                sb.deleteCharAt(sb.length() - 1);
                result.add(sb.toString());
                sb.delete(0, sb.length());
            }
            return result;
        }

        private List<List<String>> dfs(String s, Map<Integer, List<List<String>>> map, List<String> wordDict, int index) {
            if (map.containsKey(index)) {
                return map.get(index);
            }
            List<List<String>> result = new ArrayList<>();
            map.put(index, result);

            for (String word : wordDict) {
                if (s.startsWith(word, index)) {
                    if (index + word.length() == s.length()) {
                        result.add(Collections.singletonList(word));
                        continue;
                    }
                    List<List<String>> subResult = dfs(s, map, wordDict, index + word.length());
                    for (List<String> res : subResult) {
                        List<String> list = new ArrayList<>();
                        list.add(word);
                        list.addAll(res);
                        result.add(list);
                    }
                }
            }
            return result;
        }
    }

    /**
     * 动态规划
     */
    public static class Solution3 {
        public List<String> wordBreak(String s, List<String> wordDict) {
            int n = s.length();
            Set<String> wordSet = new HashSet<>(wordDict);
            List<String>[] dp = new List[n + 1];
            List<String> initial = new ArrayList<>();
            initial.add("");
            dp[0] = initial;
            for (int i = 1; i <= n; i++) {
                dp[i] = new ArrayList<>();
                for (int j = 0; j < i; j++) {
                    String sub = s.substring(j, i);
                    if (wordSet.contains(sub) && dp[j].size() > 0) {
                        for (String prefix : dp[j]) {
                            dp[i].add(prefix + (prefix.isEmpty() ? "" : " ") + sub);
                        }
                    }
                }
            }
            return dp[n];
        }
    }

    /**
     * 动态规划,使用StringBuilder优化
     */
    public static class Solution4 {
        public List<String> wordBreak(String s, List<String> wordDict) {
            int n = s.length();
            Set<String> wordSet = new HashSet<>(wordDict);
            List<String> result = new ArrayList<>();

            boolean[] match = new boolean[n + 1];
            match[0] = true;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < i; j++) {
                    if (wordSet.contains(s.substring(j, i)) && match[j]) {
                        match[i] = true;
                        break;
                    }
                }
            }
            if (!match[n]) {
                return result;
            }

            List<List<String>>[] dp = new List[n + 1];
            dp[0] = new ArrayList<>();
            dp[0].add(new ArrayList<>());
            for (int i = 1; i <= n; i++) {
                dp[i] = new ArrayList<>();
                for (int j = 0; j < i; j++) {
                    String sub = s.substring(j, i);
                    if (wordSet.contains(sub)) {
                        for (List<String> list : dp[j]) {
                            List<String> newList = new ArrayList<>(list);
                            newList.add(sub);
                            dp[i].add(newList);
                        }
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for (List<String> list : dp[n]) {
                sb.delete(0, sb.length());
                for (String word : list) {
                    sb.append(word).append(" ");
                }
                sb.deleteCharAt(sb.length() - 1);
                result.add(sb.toString());
            }
            return result;
        }
    }
}
