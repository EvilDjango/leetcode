package com.deerhunter.topic;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *  
 * <p>
 * 提示：
 * <p>
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-29
 */
public class Topic079 {
    public static class Solution1 {
        public boolean exist(char[][] board, String word) {
            int R = board.length;
            int C = board[0].length;
            if (word == null || word.length() == 0 || word.length() > R * C) {
                return false;
            }
            char first = word.charAt(0);
            // 搜索起始点集合
            List<int[]> startPositions = new ArrayList<>();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (board[i][j] == first) {
                        startPositions.add(new int[]{i, j});
                    }
                }
            }
            for (int[] startPosition : startPositions) {
                if (findWord(board, word, 0, startPosition[0], startPosition[1])) {
                    return true;
                }
            }
            return false;
        }

        private boolean findWord(char[][] board, String word, int wordIndex, int row, int col) {
            if (wordIndex == word.length()) {
                return true;
            }
            if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
                return false;
            }

            if (word.charAt(wordIndex) == board[row][col]) {
                char temp = board[row][col];
                board[row][col] = ' ';
                boolean found = findWord(board, word, wordIndex + 1, row - 1, col) ||
                        findWord(board, word, wordIndex + 1, row + 1, col) ||
                        findWord(board, word, wordIndex + 1, row, col - 1) ||
                        findWord(board, word, wordIndex + 1, row, col + 1);
                board[row][col] = temp;
                return found;
            }

            return false;

        }
    }
}
