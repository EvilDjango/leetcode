package com.deerhunter;

import java.util.Arrays;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-12
 */
public class Topic059 {
    public static class Solution1 {
        public int[][] generateMatrix(int n) {
            int[][] matrix = new int[n][n];

            // 行下标增量
            int[] dr = {0, 1, 0, -1};
            // 列下标增量
            int[] dc = {1, 0, -1, 0};
            // 方向，【0，3】分别表示向右，向下，向左，向上的4个方向
            int direction = 0;
            int row = 0, col = 0;
            for (int i = 0; i < n * n; i++) {
                matrix[row][col] = i + 1;
                int nextRow = row + dr[direction];
                int nextCol = col + dc[direction];
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || matrix[nextRow][nextCol] != 0) {
                    direction = (direction + 1) % 4;
                }
                row += dr[direction];
                col += dc[direction];
            }
            return matrix;
        }
    }
}
