package com.deerhunter;

import java.util.Arrays;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-18
 */
public class Solution036 {
    public static boolean isValidSudoku(char[][] board) {
        if (board == null) {
            return false;
        }
        boolean[][] row = new boolean[9][9];
        boolean[][] column = new boolean[9][9];
        boolean[][] block = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int index = c - '1';
                if (row[i][index]) {
                    return false;
                }
                if (column[j][index]) {
                    return false;
                }

                int blockIndex = j/3+(i/3)*3;
                if (block[blockIndex][index]) {
                    return false;
                }
                row[i][index] = true;
                column[j][index] = true;
                block[blockIndex][index] = true;
            }
        }
        return true;
    }
}