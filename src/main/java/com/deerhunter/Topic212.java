package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 212. Word Search II
 * Hard
 * <p>
 * 3450
 * <p>
 * 142
 * <p>
 * Add to List
 * <p>
 * Share
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 * Example 2:
 * <p>
 * <p>
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] is a lowercase English letter.
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/2/24 11:02
 */
public class Topic212 {
    /**
     * 朴素方法
     */
    public static class Solution1 {
        public List<String> findWords(char[][] board, String[] words) {
            int m = board.length;
            int n = board[0].length;
            // 记录每个字母的位置
            Map<Character, List<int[]>> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    char c = board[i][j];
                    if (!map.containsKey(c)) {
                        map.put(c, new ArrayList<>());
                    }
                    map.get(c).add(new int[]{i, j});
                }
            }
            List<String> ans = new ArrayList<>();
            for (String word : words) {
                if (!map.containsKey(word.charAt(0))) {
                    continue;
                }
                for (int[] position : map.get(word.charAt(0))) {
                    boolean[][] visited = new boolean[m][n];
                    if (dfs(board, visited, position[0], position[1], word, 1)) {
                        ans.add(word);
                        break;
                    }
                }
            }
            return ans;
        }

        private boolean dfs(char[][] board, boolean[][] visited, int row, int col, String word, int index) {
            if (index == word.length()) {
                return true;
            }
            visited[row][col] = true;
            int m = board.length;
            int n = board[0].length;
            int[][] adjacentPositions = {{row - 1, col}, {row + 1, col}, {row, col - 1}, {row, col + 1}};
            for (int[] position : adjacentPositions) {
                int r = position[0];
                int c = position[1];
                if (r >= 0 && r < m && c >= 0 && c < n && !visited[r][c] && board[r][c] == word.charAt(index) && dfs(board, visited, r, c, word, index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 使用前缀树
     */
    public static class Solution2 {
        public static class TrieNode {
            private String word;
            private Map<Character, TrieNode> children = new HashMap<>();
        }

        public List<String> findWords(char[][] board, String[] words) {
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode node = root;
                for (int i = 0; i < word.length(); i++) {
                    Character c = word.charAt(i);
                    if (!node.children.containsKey(c)) {
                        node.children.put(c, new TrieNode());
                    }
                    node = node.children.get(c);
                }
                node.word = word;
            }
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    backtrack(board, root, i, j, ans);
                }
            }
            return ans;
        }

        private void backtrack(char[][] board, TrieNode node, int row, int col, List<String> ans) {
            if (row < 0 || row == board.length || col < 0 || col == board[0].length) {
                return;
            }
            char c = board[row][col];
            TrieNode child = node.children.get(c);
            if (child == null) {
                return;
            }
            if (child.word != null) {
                ans.add(child.word);
                child.word = null;
            }
            // 防止重复遍历
            board[row][col] = '#';
            int[][] nextPositions = {{row - 1, col}, {row + 1, col}, {row, col - 1}, {row, col + 1}};
            for (int[] nextPosition : nextPositions) {
                backtrack(board, child, nextPosition[0], nextPosition[1], ans);
            }
            board[row][col] = c;
            // 同一个单词只需要找到一个匹配即可
            if (child.children.isEmpty()) {
                node.children.remove(c);
            }
        }
    }
}
