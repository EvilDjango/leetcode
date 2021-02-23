package com.deerhunter;

import java.util.HashSet;
import java.util.Set;

/**
 * 211. Design Add and Search Words Data Structure
 * Medium
 * <p>
 * 2756
 * <p>
 * 120
 * <p>
 * Add to List
 * <p>
 * Share
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 * <p>
 * Implement the WordDictionary class:
 * <p>
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 * <p>
 * <p>
 * Example:
 * <p>
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 * <p>
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= word.length <= 500
 * word in addWord consists lower-case English letters.
 * word in search consist of  '.' or lower-case English letters.
 * At most 50000 calls will be made to addWord and search.
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/2/23 15:49
 */
public class Topic211 {
    /**
     * 暴力算法,会超时
     */
    public static class Solution1 {
        class WordDictionary {
            private Set<String> dict = new HashSet<>();

            /**
             * Initialize your data structure here.
             */
            public WordDictionary() {

            }

            public void addWord(String word) {
                dict.add(word);
            }

            public boolean search(String word) {
                if (dict.contains(word)) {
                    return true;
                }
                for (String dictWord : dict) {
                    if (matches(word, dictWord)) {
                        return true;
                    }
                }
                return false;
            }

            public boolean matches(String pattern, String word) {
                if (pattern.length() != word.length()) {
                    return false;
                }
                for (int i = 0; i < pattern.length(); i++) {
                    if (pattern.charAt(i) != '.' && pattern.charAt(i) != word.charAt(i)) {
                        return false;
                    }
                }
                return true;
            }
        }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
    }

    /**
     * 使用前缀树
     */
    public static class Solution2 {
        public static class WordDictionary {
            private TreeNode root = new TreeNode();

            /**
             * Initialize your data structure here.
             */
            public WordDictionary() {

            }

            public void addWord(String word) {
                TreeNode node = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (!node.containsKey(c)) {
                        node.add(c);
                    }
                    node = node.get(c);
                }
                node.isEnd = true;
            }

            public boolean search(String word) {
                return search(root, word, 0);
            }

            private boolean search(TreeNode node, String word, int index) {
                if (node == null) {
                    return false;
                }
                if (index == word.length()) {
                    return node.isEnd;
                }
                char c = word.charAt(index);
                if (c == '.') {
                    for (int i = 0; i < 26; i++) {
                        if (search(node.get(i), word, index + 1)) {
                            return true;
                        }
                    }
                } else if (search(node.get(c), word, index + 1)) {
                    return true;
                }

                return false;
            }

            private static class TreeNode {
                private boolean isEnd;
                public TreeNode[] children = new TreeNode[26];

                public boolean containsKey(char c) {
                    return children[c - 'a'] != null;
                }

                public TreeNode get(char c) {
                    return children[c - 'a'];
                }

                public TreeNode get(int i) {
                    return children[i];
                }

                public TreeNode add(char c) {
                    children[c - 'a'] = new TreeNode();
                    return children[c - 'a'];
                }
            }
        }
    }
}
