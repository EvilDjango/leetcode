package com.deerhunter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 244. 最短单词距离 II
 * 请设计一个类，使该类的构造函数能够接收一个单词列表。然后再实现一个方法，该方法能够分别接收两个单词 word1 和 word2，并返回列表中这两个单词之间的最短距离。您的方法将被以不同的参数调用 多次。
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
 * 通过次数4,324提交次数7,957
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/20/21 11:32 AM
 */
public class Topic244 {
    /**
     * 会超时
     */
    public static class WordDistance {
        private Map<String, Map<String, Integer>> map = new HashMap<>();

        public WordDistance(String[] wordsDict) {
            Map<String, Integer> lastIndexes = new HashMap<>();
            for (int i = 0; i < wordsDict.length; i++) {
                String word2 = wordsDict[i];
                for (String word1 : lastIndexes.keySet()) {
                    if (word1.equals(word2)) {
                        continue;
                    }
                    map.putIfAbsent(word1, new HashMap<>());
                    map.putIfAbsent(word2, new HashMap<>());
                    int min = Math.min(i - lastIndexes.get(word1), map.get(word1).getOrDefault(word2, Integer.MAX_VALUE));
                    map.get(word1).put(word2, min);
                    map.get(word2).put(word1, min);
                }
                lastIndexes.put(word2, i);
            }
        }


        public int shortest(String word1, String word2) {
            return map.get(word1).get(word2);
        }
    }


    /**
     * 在每次调用shortest方法时计算，并缓存结果
     */
    public static class WordDistance2 {
        private String[] words;
        private Map<String, Map<String, Integer>> cache;

        public WordDistance2(String[] wordsDict) {
            words = wordsDict;
            cache = new HashMap<>();
        }


        public int shortest(String word1, String word2) {
            if (!cache.containsKey(word1) || !cache.get(word1).containsKey(word2)) {
                int shortest = getShortest(word1, word2);
                cache.putIfAbsent(word1, new HashMap<>());
                cache.putIfAbsent(word2, new HashMap<>());
                cache.get(word1).put(word2, shortest);
                cache.get(word2).put(word1, shortest);
            }
            return cache.get(word1).get(word2);
        }

        private int getShortest(String word1, String word2) {
            int shortest = Integer.MAX_VALUE;
            int i1 = -1, i2 = -1;
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word1)) {
                    i1 = i;
                } else if (words[i].equals(word2)) {
                    i2 = i;
                } else {
                    continue;
                }
                if (i1 != -1 && i2 != -1) {
                    shortest = Math.min(shortest, Math.abs(i2 - i1));
                }
            }
            return shortest;
        }
    }

    /**
     * 参考题解
     */
    public static class WordDistance3 {
        private Map<String, List<Integer>> locations;

        public WordDistance3(String[] wordsDict) {
            locations = new HashMap<>();
            for (int i = 0; i < wordsDict.length; i++) {
                locations.putIfAbsent(wordsDict[i], new ArrayList<>());
                locations.get(wordsDict[i]).add(i);
            }
        }

        public int shortest(String word1, String word2) {
            int ans = Integer.MAX_VALUE;
            List<Integer> loc1 = locations.get(word1);
            List<Integer> loc2 = locations.get(word2);
            int i1 = 0;
            int i2 = 0;
            while (i1 < loc1.size() && i2 < loc2.size()) {
                ans = Math.min(ans, Math.abs(loc1.get(i1) - loc2.get(i2)));
                if (loc1.get(i1) > loc2.get(i2)) {
                    i2++;
                } else {
                    i1++;
                }
            }
            return ans;
        }
    }
}
