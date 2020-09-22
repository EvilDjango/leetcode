package com.deerhunter;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 示例:
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * <p>
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/20 19:19
 */
public class Topic130 {
    /**
     * 深度优先遍历
     */
    public static class Solution1 {
        public void solve(char[][] board) {
            int rows = board.length;
            int cols;
            if (rows == 0 || (cols = board[0].length) == 0) {
                return;
            }
            boolean[][] keep = new boolean[rows][cols];
            int row = 0, col = 0;
            while (true) {
                dfs(board, keep, row, col);
                if (row == 0 && col < cols - 1) {
                    col++;
                } else if (col == cols - 1 && row < rows - 1) {
                    row++;
                } else if (row == rows - 1 && col > 0) {
                    col--;
                } else if (col == 0 && row > 0) {
                    row--;
                }
                if (col == 0 && row == 0) {
                    break;
                }
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] == 'O' && !keep[i][j]) {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        private void dfs(char[][] board, boolean[][] keep, int row, int col) {
            if (row < 0 || col < 0 || row == board.length || col == board[0].length) {
                return;
            }
            if (keep[row][col]) {
                return;
            }
            if (board[row][col] == 'O') {
                keep[row][col] = true;
                dfs(board, keep, row - 1, col);
                dfs(board, keep, row + 1, col);
                dfs(board, keep, row, col - 1);
                dfs(board, keep, row, col + 1);
            }
        }
    }

    /**
     * 广度优先遍历
     */
    public static class Solution2 {
        public void solve(char[][] board) {
            int rows = board.length;
            int cols;
            if (rows == 0 || (cols = board[0].length) == 0) {
                return;
            }
            boolean[][] keep = new boolean[rows][cols];
            int row = 0, col = 0;
            while (true) {
                bfs(board, keep, row, col);
                if (row == 0 && col < cols - 1) {
                    col++;
                } else if (col == cols - 1 && row < rows - 1) {
                    row++;
                } else if (row == rows - 1 && col > 0) {
                    col--;
                } else if (col == 0 && row > 0) {
                    row--;
                }
                if (col == 0 && row == 0) {
                    break;
                }
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] == 'O' && !keep[i][j]) {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        private void bfs(char[][] board, boolean[][] keep, int row, int col) {
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{row, col});
            while (!queue.isEmpty()) {
                int[] coordinate = queue.remove();
                int r = coordinate[0];
                int c = coordinate[1];
                if (r < 0 || r == board.length || c < 0 || c == board[0].length) {
                    continue;
                }
                if (!keep[r][c] && board[r][c] == 'O') {
                    keep[r][c] = true;
                    queue.add(new int[]{r - 1, c});
                    queue.add(new int[]{r + 1, c});
                    queue.add(new int[]{r, c - 1});
                    queue.add(new int[]{r, c + 1});
                }
            }
        }
    }
}
