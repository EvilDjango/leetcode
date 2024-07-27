package com.deerhunter.topic;

import java.util.*;


/**
 * 兴业数金的一道笔试题。
 * 题目要求是从输入的字符串数组中找到一个满足条件的最长的字符串，这个字符串必须是由两个及以上其他字符串组合而成的。
 * 如果没有满足条件的字符串，返回空字符串。
 */
public class XingyeExam {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param words string字符串一维数组
     * @return string字符串
     */
    public String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        int len = words.length;
        Trie root = new Trie();
        for (int i = 0; i < len; i++) {
            words[i] = words[i].toLowerCase();
            root.insert(words[i]);
        }
        Arrays.sort(words, Comparator.comparingInt(String::length).reversed());
        List<String> longests = new ArrayList<>();
        int maxLen = 0;
        for (String word : words) {
            // 找到潜在的答案后，长度小于此答案的字符串可以忽略
            if (longests.size() > 0 && word.length() < maxLen) {
                break;
            }
            if (isCombinedByOthers(root, word, 0)) {
                longests.add(word);
                maxLen = word.length();
            }
        }
        if (longests.size() == 0) {
            return "";
        }
        Collections.sort(longests);
        return longests.get(0);
    }

    private boolean isCombinedByOthers(Trie root, String target, int index) {
        Trie cur = root;
        for (int i = index; i < target.length(); i++) {
            cur = cur.children[target.charAt(i) - 'a'];
            if (cur == null) {
                break;
            }
            if (cur.word != null) {
                // 排除由自己组成自己的情况
                if (i == target.length() - 1 && !Objects.equals(cur.word, target)) {
                    return true;
                }
                if (isCombinedByOthers(root, target, i + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

//    private boolean isCombinedByOthers(String[] words, String target, boolean isRoot) {
//        for (String word : words) {
//            if (isRoot && Objects.equals(word, target)) {
//                continue;
//            }
//            if (target.startsWith(word)) {
//                return word.length() == target.length() || isCombinedByOthers(words, target.substring(word.length()), false);
//            }
//        }
//        return false;
//    }

    /**
     * 字典树
     */
    private class Trie {
        Trie[] children = new Trie[26];
        String word;


        public void insert(String word) {
            Trie cur = this;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new Trie();
                }
                cur = cur.children[index];
            }
            cur.word = word;
        }

        public Trie search(String word) {
            Trie cur = this;
            for (int i = 0; i < word.length() && cur != null; i++) {
                cur = cur.children[word.charAt(i) - 'a'];
            }
            return cur;
        }
    }
}
