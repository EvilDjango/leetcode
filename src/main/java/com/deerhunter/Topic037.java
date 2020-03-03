package com.deerhunter;

import lombok.extern.slf4j.Slf4j;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * <p>
 * <p>
 * <p>
 * 一个数独。
 * <p>
 * <p>
 * <p>
 * 答案被标成红色。
 * <p>
 * Note:
 * <p>
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-10-23
 */
@Slf4j
public class Topic037 {
    // 自己写的回溯算法
    public static boolean solveSudoku(char[][] board) {
        return dfsFill(board, 0, 0);
    }

    /**
     * 递归填充格子
     *
     * @param board
     * @param row
     * @param col
     */
    private static boolean dfsFill(char[][] board, int row, int col) {
        if (row == -1) {
            return true;
        }

        int[] next = getNext(row, col);

        // 最初已经确定了值的格子无需操作，直接走向下一格
        if ('.' != board[row][col]) {
            return dfsFill(board, next[0], next[1]);
        }

        boolean[] book = getAvailableDigitBook(board, row, col);
        System.out.print("");
        // 没有确定值的格子，依次尝试1-9
        for (int i = 0; i < 9; i++) {
            if (book[i]) {
                continue;
            }
            char c = (char) ('1' + i);
            board[row][col] = c;
            if (dfsFill(board, next[0], next[1])) {
                return true;
            }
            // 若行不通则将此项重置，后面可以重新尝试
            board[row][col] = '.';
        }
        return false;
    }

    /**
     * 获取当前格子（坐标为（row，col））可以使用的数字的记录表
     *
     * @param board
     * @param row
     * @param col
     * @return
     */
    private static boolean[] getAvailableDigitBook(char[][] board, int row, int col) {
        boolean[] book = new boolean[9];

        // 统计所在的行
        for (char c : board[row]) {
            if (c != '.') {
                book[c - '1'] = true;
            }
        }

        // 统计所在的列
        for (int i = 0; i < board.length; i++) {
            char c = board[i][col];
            if (c != '.') {
                book[c - '1'] = true;
            }
        }

        // 统计所在的块区域
        int[] position = getBlockTopLeft(row, col);
        int startRow = position[0];
        int startCol = position[1];
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                char c = board[i][j];
                if (c != '.') {
                    book[c - '1'] = true;
                }
            }
        }

        return book;
    }

    /**
     * 获取当前位置所属区块的左上角位置
     *
     * @param row
     * @param col
     * @return
     */
    static int[] getBlockTopLeft(int row, int col) {
        int topRow = row - row % 3;
        int leftCol = col - col % 3;
        return new int[]{topRow, leftCol};
    }

    /**
     * 获取下一个位置。按照从左到右，从上到下的顺序遍历。
     *
     * @param row
     * @param col
     * @return
     */
    static int[] getNext(int row, int col) {
        int nextCol = (col + 1) % 9;

        // 没有换行
        if (nextCol > col) {
            return new int[]{row, nextCol};
        }

        // 换行
        int nextRow = ++row;

        // 已经遍历完成
        if (nextRow > 8) {
            return new int[]{-1, -1};
        }

        return new int[]{nextRow, nextCol};
    }

    /**
     * 参考官方题解写的解法，与自己的解法的区别在于，这里增加了对各行，列，方块已使用数字的缓存，避免了重复计算，提高了效率
     */
    static class OfficialSolution {
        /**
         * 是否已经得到了一个解
         */
        private boolean solved = false;
        /**
         * 数独的阶数
         */
        private int N = 9;
        /**
         * 方块边长
         */
        private int BOX_LEN = 3;
        private char[][] board;
        /**
         * 记录各行已经使用的数字
         */
        private boolean[][] ROW_BOOK = new boolean[N][N + 1];
        /**
         * 记录各列已经使用的数字
         */
        private boolean[][] COL_BOOK = new boolean[N][N + 1];
        /**
         * 记录各个方块中已经使用的数字
         */
        private boolean[][] BOX_BOOK = new boolean[N][N + 1];

        public boolean solveSudoku(char[][] board) {
            this.board = board;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    char c = board[i][j];
                    if (c != '.') {
                        int d = Character.getNumericValue(c);
                        placeNumber(d, i, j);
                    }
                }
            }
            backTrack(0, 0);
            return solved;
        }

        private void backTrack(int row, int col) {
            if (row == -1) {
                solved = true;
                return;
            }
            int[] next = getNext(row, col);
            int nextRow = next[0];
            int nextCol = next[1];
            if (board[row][col] != '.') {
                backTrack(nextRow, nextCol);
            } else {
                for (int d = 1; d < 10; d++) {
                    if (couldPlace(d, row, col)) {
                        placeNumber(d, row, col);
                        backTrack(nextRow, nextCol);
                        if (!solved) {
                            removeNumber(d, row, col);
                        }
                    }
                }
            }
        }


        /**
         * 放置一个数字
         *
         * @param d
         * @param row
         * @param col
         */
        private void placeNumber(int d, int row, int col) {
            board[row][col] = (char) ('0' + d);
            ROW_BOOK[row][d] = true;
            COL_BOOK[col][d] = true;
            BOX_BOOK[getBoxIndex(row, col)][d] = true;
        }

        /**
         * 移除一个数字
         *
         * @param d
         * @param row
         * @param col
         */
        private void removeNumber(int d, int row, int col) {
            board[row][col] = '.';
            ROW_BOOK[row][d] = false;
            COL_BOOK[col][d] = false;
            BOX_BOOK[getBoxIndex(row, col)][d] = false;
        }

        /**
         * 判断一个数字d是否可以放在当前位置（row，col）
         *
         * @param d
         * @param row
         * @param col
         * @return
         */
        private boolean couldPlace(int d, int row, int col) {
            int boxIndex = getBoxIndex(row, col);
            // 如果一个数字在当前行，当前列，当前方块内都没有出现过，则可以放置在当前位置
            return !ROW_BOOK[row][d] && !COL_BOOK[col][d] && !BOX_BOOK[boxIndex][d];
        }

        /**
         * 获取方块编号
         *
         * @param row
         * @param col
         * @return
         */
        private int getBoxIndex(int row, int col) {
            return (row / BOX_LEN) * (N / BOX_LEN) + col / BOX_LEN;
        }

    }

}
