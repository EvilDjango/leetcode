package com.deerhunter;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.deerhunter.common.Utils.arrToStr;

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
public class Solution037 {
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

        if (row == 6) {
            log.info("\n" + arrToStr(board));
        }
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

    public static void main(String[] args) throws IOException {
        File f = new File("test");
        FileWriter writer = new FileWriter(f);
        writer.append("test");
        writer.close();
        log.info("INFO test");
    }

}