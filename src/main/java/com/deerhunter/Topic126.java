package com.deerhunter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
 * <p>
 * 每次转换只能改变一个字母。
 * 转换后得到的单词必须是字典中的单词。
 * 说明:
 * <p>
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出:
 * [
 * ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * 示例 2:
 * <p>
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: []
 * <p>
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/16 22:34
 */
public class Topic126 {
    /**
     * 暴力算法
     */
    public static class Solution1 {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            List<List<String>> result = new ArrayList<>();
            Map<String, Boolean> used = wordList.stream().collect(Collectors.toMap(s -> s, s -> false));
            used.put(beginWord, Boolean.TRUE);
            List<String> current = new ArrayList<>();
            current.add(beginWord);
            Map<String, Set<String>> adjacentMap = new HashMap<>();
            List<String> myList = new ArrayList<>(wordList);
            myList.add(beginWord);
            for (int i = 0; i < myList.size(); i++) {
                for (int j = i + 1; j < myList.size(); j++) {
                    String s1 = myList.get(i);
                    String s2 = myList.get(j);
                    if (isAdjacent(s1, s2)) {
                        putIn(adjacentMap, s1, s2);
                        putIn(adjacentMap, s2, s1);
                    }
                }
            }
            myList.remove(myList.size() - 1);
            dfs(beginWord, endWord, used, adjacentMap, result, current);
            int min = Integer.MAX_VALUE;
            for (List<String> list : result) {
                min = Math.min(min, list.size());
            }
            Iterator<List<String>> iterator = result.iterator();
            while (iterator.hasNext()) {
                List<String> list = iterator.next();
                if (list.size() != min) {
                    iterator.remove();
                }
            }
            return result;
        }

        private void putIn(Map<String, Set<String>> adjacentMap, String s1, String s2) {
            if (!adjacentMap.containsKey(s1)) {
                adjacentMap.put(s1, new HashSet<>());
            }
            adjacentMap.get(s1).add(s2);
        }

        private void dfs(String beginWord, String endWord, Map<String, Boolean> used, Map<String, Set<String>> adjacentMap, List<List<String>> result, List<String> current) {
            if (beginWord.equals(endWord)) {
                result.add(new ArrayList<>(current));
                return;
            }
            Set<String> adjacentWords = adjacentMap.get(beginWord);
            if (adjacentWords == null) {
                return;
            }
            for (String word : adjacentWords) {
                if (!Boolean.TRUE.equals(used.get(word))) {
                    current.add(word);
                    used.put(word, Boolean.TRUE);
                    dfs(word, endWord, used, adjacentMap, result, current);
                    current.remove(word);
                    used.put(word, Boolean.FALSE);
                }
            }
        }
    }

    /**
     * 广度遍历.
     */
    public static class Solution2 {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            List<List<String>> result = new ArrayList<>();
            if (!wordList.contains(endWord)) {
                return result;
            }

            List<String> myList = new ArrayList<>(wordList);
            if (!myList.contains(beginWord)) {
                myList.add(beginWord);
            }
            Map<String, Set<String>> adjacentMap = new HashMap<>();
            for (int i = 0; i < myList.size(); i++) {
                for (int j = i + 1; j < myList.size(); j++) {
                    String s1 = myList.get(i);
                    String s2 = myList.get(j);
                    if (isAdjacent(s1, s2)) {
                        putIn(adjacentMap, s1, s2);
                        putIn(adjacentMap, s2, s1);
                    }
                }
            }

            Set<String> used = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            Queue<List<String>> paths = new LinkedList<>();
            queue.add(beginWord);
            // 用null来分隔每一层
            queue.add(null);
            paths.add(new ArrayList<>());
            paths.add(null);
            while (!queue.isEmpty()) {
                String word = queue.remove();
                List<String> path = paths.remove();
                if (word != null) {
                    used.add(word);
                    Set<String> adjacentWords = adjacentMap.get(word);
                    if (adjacentWords == null) {
                        continue;
                    }
                    for (String adjacentWord : adjacentWords) {
                        if (used.contains(adjacentWord)) {
                            continue;
                        }
                        List<String> newPath = new ArrayList<>(path);
                        newPath.add(word);
                        // 如果已经走到了目标词，那么没有必要继续扩展这条路径了
                        if (adjacentWord.equals(endWord)) {
                            newPath.add(adjacentWord);
                            result.add(newPath);
                        } else {
                            queue.add(adjacentWord);
                            paths.add(newPath);
                        }
                    }
                } else {
                    // 如果已经找到了最短路径，那么到下一层必然就不是最短路径了，跳出循环
                    if (result.size() > 0) {
                        break;
                    }
                    if (queue.isEmpty()) {
                        break;
                    }
                    queue.add(null);
                    paths.add(null);
                }
            }
            return result;
        }


        private void putIn(Map<String, Set<String>> adjacentMap, String s1, String s2) {
            if (!adjacentMap.containsKey(s1)) {
                adjacentMap.put(s1, new HashSet<>());
            }
            adjacentMap.get(s1).add(s2);
        }

    }

    /**
     * 模仿官方题解，本质上是队列优化的Bellman-Ford算法
     */
    public static class Solution3 {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            List<List<String>> result = new ArrayList<>();
            if (!wordList.contains(endWord)) {
                return result;
            }

            List<String> words = new ArrayList<>(wordList);
            if (!words.contains(beginWord)) {
                words.add(beginWord);
            }
            int beginWordIndex = words.indexOf(beginWord);
            int endWordIndex = words.indexOf(endWord);

            List<Integer>[] edges = new List[words.size()];
            for (int i = 0; i < words.size(); i++) {
                edges[i] = new ArrayList<>();
            }
            for (int i = 0; i < words.size(); i++) {
                for (int j = i + 1; j < words.size(); j++) {
                    if (isAdjacent(words.get(i), words.get(j))) {
                        edges[i].add(j);
                        edges[j].add(i);
                    }
                }
            }
            int[] distance = new int[words.size()];
            for (int i = 0; i < words.size(); i++) {
                distance[i] = Integer.MAX_VALUE;
            }
            distance[beginWordIndex] = 0;

            // 队列中最后一个元素代表当前节点，前面那些元素表示一路走来的路径
            Queue<List<Integer>> queue = new ArrayDeque<>();
            List<Integer> begin = new ArrayList<>();
            begin.add(beginWordIndex);
            queue.add(begin);

            while (!queue.isEmpty()) {
                List<Integer> path = queue.remove();
                Integer u = path.get(path.size() - 1);
                // 达到目标节点后，停止扩展
                if (u == endWordIndex) {
                    List<String> pathWords = path.stream().map(words::get).collect(Collectors.toList());
                    result.add(pathWords);
                } else {
                    for (Integer v : edges[u]) {
                        if (distance[u] + 1 <= distance[v]) {
                            distance[v] = distance[u] + 1;
                            List<Integer> newPath = new ArrayList<>(path);
                            newPath.add(v);
                            queue.add(newPath);
                        }
                    }
                }
            }

            return result;
        }
    }

    /**
     * dijkstra算法
     */
    public static class Solution4 {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            List<List<String>> result = new ArrayList<>();
            if (!wordList.contains(endWord)) {
                return result;
            }

            List<String> words = new ArrayList<>(wordList);
            if (!words.contains(beginWord)) {
                words.add(beginWord);
            }

            int beginWordIndex = words.indexOf(beginWord);
            int endWordIndex = words.indexOf(endWord);

            int n = words.size();
            List<Integer>[] edges = new List[n];
            for (int i = 0; i < n; i++) {
                edges[i] = new ArrayList<>();
            }
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (isAdjacent(words.get(i), words.get(j))) {
                        edges[i].add(j);
                        edges[j].add(i);
                    }
                }
            }
            int[] distance = new int[n];
            for (int i = 0; i < n; i++) {
                distance[i] = Integer.MAX_VALUE;
            }
            distance[beginWordIndex] = 0;
            // pathsOfNode[i]表示从起点到i节点的可能的最短路径
            List<List<Integer>>[] pathsOfNode = new List[n];
            for (int i = 0; i < n; i++) {
                pathsOfNode[i] = new ArrayList<>();
            }
            boolean[] solved = new boolean[n];
            List<Integer> beginPath = new ArrayList<>();
            beginPath.add(beginWordIndex);
            pathsOfNode[beginWordIndex].add(beginPath);
            for (int i = 0; i < n; i++) {
                int shortest = -1;
                for (int j = 0; j < n; j++) {
                    if (!solved[j] && (shortest == -1 || distance[j] < distance[shortest])) {
                        shortest = j;
                    }
                }

                // 如果已经达到目标节点，后面无需继续进行
                if (shortest == endWordIndex) {
                    break;
                }
                for (Integer adjacentNode : edges[shortest]) {
                    if (!solved[adjacentNode] && distance[shortest] + 1 <= distance[adjacentNode]) {
                        distance[adjacentNode] = distance[shortest] + 1;
                        // 找到更短的路径，则舍去之前较长的路径
                        if (distance[shortest] + 1 < distance[adjacentNode]) {
                            pathsOfNode[adjacentNode].clear();
                        }
                        for (List<Integer> path : pathsOfNode[shortest]) {
                            List<Integer> newPath = new ArrayList<>(path);
                            newPath.add(adjacentNode);
                            pathsOfNode[adjacentNode].add(newPath);
                        }
                    }
                }
                solved[shortest] = true;
            }
            for (List<Integer> path : pathsOfNode[endWordIndex]) {
                result.add(path.stream().map(words::get).collect(Collectors.toList()));
            }
            return result;
        }
    }

    private static boolean isAdjacent(String s1, String s2) {
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
