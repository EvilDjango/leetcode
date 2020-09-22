package com.deerhunter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * <p>
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * <p>
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出: 5
 * <p>
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * 示例 2:
 * <p>
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: 0
 * <p>
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/18 17:57
 */
public class Topic127 {
    /**
     * bellman-ford最短路径算法
     */
    public static class Solution1 {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            List<String> words = new ArrayList<>(wordList);
            if (!words.contains(beginWord)) {
                words.add(beginWord);
            }
            int endWordIndex = words.indexOf(endWord);
            if (endWordIndex == -1) {
                return 0;
            }
            int beginWordIndex = words.indexOf(beginWord);

            int n = words.size();
            List<Integer>[] edges = new List[n];
            for (int i = 0; i < n; i++) {
                edges[i] = new ArrayList<>();
            }
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (couldTransform(words.get(i), words.get(j))) {
                        edges[i].add(j);
                        edges[j].add(i);
                    }
                }
            }
            int[] distance = new int[n];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[beginWordIndex] = 0;
            boolean[] used = new boolean[n];
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(beginWordIndex);
            used[beginWordIndex] = true;
            while (!queue.isEmpty()) {
                Integer word = queue.remove();
                used[word] = false;

                // 目标节点之后的节点无需再进行松弛
                if (word == endWordIndex) {
                    continue;
                }
                for (Integer neighbor : edges[word]) {
                    if (distance[word] + 1 < distance[neighbor]) {
                        distance[neighbor] = distance[word] + 1;
                        if (!used[neighbor]) {
                            queue.add(neighbor);
                        }
                    }
                }
            }
            return distance[endWordIndex] == Integer.MAX_VALUE ? 0 : +distance[endWordIndex] + 1;
        }
    }

    /**
     * 双向广度遍历
     */
    public static class Solution2 {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) {
                return 0;
            }
            Map<String, Set<String>> patternWords = new HashMap<>();
            for (int i = 0; i < wordList.size(); i++) {
                String word = wordList.get(i);
                putInWord(patternWords, word);
            }
            putInWord(patternWords, beginWord);

            Queue<String> beginQueue = new LinkedList<>();
            beginQueue.add(beginWord);
            beginQueue.add(null);
            Set<String> beginVisited = new HashSet<>();
            beginVisited.add(beginWord);

            Queue<String> endQueue = new LinkedList<>();
            endQueue.add(endWord);
            endQueue.add(null);
            Set<String> endVisited = new HashSet<>();
            endVisited.add(endWord);

            int distance = 2;
            while (true) {
                if (visitOneLevel(beginQueue, patternWords, beginVisited, endVisited)) {
                    return distance;
                } else if (beginQueue.isEmpty()) {
                    return 0;
                } else {
                    distance++;
                    beginQueue.add(null);
                }

                if (visitOneLevel(endQueue, patternWords, endVisited, beginVisited)) {
                    return distance;
                } else if (endQueue.isEmpty()) {
                    return 0;
                } else {
                    distance++;
                    endQueue.add(null);
                }
            }
        }

        private boolean visitOneLevel(Queue<String> queue, Map<String, Set<String>> patternWords, Set<String> visited, Set<String> visitedByAnotherDirection) {
            while (!queue.isEmpty()) {
                String word = queue.remove();
                // 遍历完了一层
                if (word == null) {
                    return false;
                }
                Set<String> adjacentWords = getAdjacentWords(word, patternWords);
                for (String adjacentWord : adjacentWords) {
                    if (!visited.contains(adjacentWord)) {
                        if (visitedByAnotherDirection.contains(adjacentWord)) {
                            return true;
                        }
                        queue.add(adjacentWord);
                        visited.add(adjacentWord);
                    }
                }
            }
            return false;
        }

        private Set<String> getAdjacentWords(String word, Map<String, Set<String>> patternWords) {
            Set<String> adjacentWordsOfEndWord = new HashSet<>();
            for (int i = 0; i < word.length(); i++) {
                String pattern = getPattern(word, i);
                adjacentWordsOfEndWord.addAll(patternWords.get(pattern));
            }
            return adjacentWordsOfEndWord;
        }

        private void putInWord(Map<String, Set<String>> patternWords, String word) {
            for (int j = 0; j < word.length(); j++) {
                String pattern = getPattern(word, j);
                if (!patternWords.containsKey(pattern)) {
                    patternWords.put(pattern, new HashSet<>());
                }
                patternWords.get(pattern).add(word);
            }
        }

        /**
         * 将指定位置替换为通配符
         *
         * @param word
         * @param wildcardIndex
         * @return
         */
        private String getPattern(String word, int wildcardIndex) {
            return word.substring(0, wildcardIndex) + "*" + word.substring(wildcardIndex + 1);
        }
    }


    public static boolean couldTransform(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
                if (diff > 1) {
                    return false;
                }
            }
        }
        return diff == 1;
    }
}
