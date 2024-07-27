package com.deerhunter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * <p>
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * <p>
 * 输入：
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * 输出：[]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-04
 */
public class Topic030 {
    /**
     * 复制的提交记录里面最快的算法
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring3(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if (words.length == 0 || s.length() < words.length * words[0].length()) {
            return list;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }
        int listLen = words.length;
        int wordLen = words[0].length();

        for (int i = 0; i < wordLen; i++) {
            for (int j = i; j <= s.length() - wordLen * listLen; j += wordLen) {
                Map<String, Integer> map2 = new HashMap<>();
                for (int k = listLen - 1; k >= 0; k--) {
                    String temp = s.substring(j + k * wordLen, j + (k + 1) * wordLen);
                    int val = map2.getOrDefault(temp, 0) + 1;
                    if (val > map.getOrDefault(temp, 0)) {
                        j += k * wordLen;
                        break;
                    }
                    if (k == 0) {
                        list.add(j);
                    } else {
                        map2.put(temp, val);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 参考题解（https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/solution/chuan-lian-suo-you-dan-ci-de-zi-chuan-by-powcai/）
     * 然后自己写的解法
     *
     * @param s
     * @param words
     * @return
     */
    public static List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> ret = new LinkedList<>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return ret;
        }

        int len = words[0].length();
        int num = words.length;
        int totalLen = len * num;

        if (totalLen > s.length()) {
            return ret;
        }
        Map<String, Integer> frequency = new HashMap<>(words.length);
        for (int i = 0; i < words.length; i++) {
            frequency.merge(words[i], 1, Integer::sum);
        }
        outer:
        for (int i = 0; i + totalLen <= s.length(); i++) {
            String sub = s.substring(i, i + totalLen);
            Map<String, Integer> tempFreq = new HashMap<>();
            for (int j = 0; j + len <= sub.length(); j += len) {
                String word = sub.substring(j, j + len);
                Integer freq = frequency.get(word);
                if (freq == null) {
                    continue outer;
                }
                tempFreq.merge(word, 1, Integer::sum);
                if (tempFreq.get(word) > frequency.get(word)) {
                    continue outer;
                }
            }
            ret.add(i);
        }
        return ret;
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new LinkedList<>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return ret;
        }

        int len = words[0].length();
        int totalLen = words[0].length() * words.length;
        if (s.length() < totalLen) {
            return ret;
        }

        outer:
        for (int i = 0; i + totalLen <= s.length(); i++) {
            String sub = s.substring(i, i + totalLen);
            for (int j = 0; j < words.length; j++) {
                String word = words[j];
                int fromIndex = 0;
                int index = sub.indexOf(word, fromIndex);
                if (index == -1) {
                    continue outer;
                }

                while (index % len != 0 && fromIndex < sub.length()) {
                    fromIndex += len;
                    index = sub.indexOf(word, fromIndex);
                }
                if (fromIndex == sub.length()) {
                    continue outer;
                }
                sub = sub.substring(0, index) + sub.substring(index + len);
            }

            ret.add(i);
        }
        return ret;
    }

}
