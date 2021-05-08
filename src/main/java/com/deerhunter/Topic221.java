package com.deerhunter;

/**
 * 221. 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：matrix = [["0"]]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/4/7 18:14
 */
public class Topic221 {
    /**
     * 暴力算法
     */
    public static class Solution1 {
        public int maximalSquare(char[][] matrix) {
            int ans = 0;
            int m = matrix.length;
            int n = matrix[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    ans = Math.max(findMaxSquare(matrix, i, j), ans);
                }
            }
            return ans;
        }

        private int findMaxSquare(char[][] matrix, int i, int j) {
            int maxLength = Math.min(matrix.length - i, matrix[0].length - j);
            int row, col;
            int length = 0;
            for (int k = 0; k < maxLength; k++) {
                row = i + k;
                col = j + k;
                if (matrix[row][col] != '1') {
                    break;
                }
                boolean isSquare = true;
                for (int l = j; l < col; l++) {
                    if (matrix[row][l] != '1') {
                        isSquare = false;
                        break;
                    }
                }
                if (!isSquare) {
                    break;
                }
                for (int l = i; l < row; l++) {
                    if (matrix[l][col] != '1') {
                        isSquare = false;
                        break;
                    }
                }
                if (!isSquare) {
                    break;
                }
                length++;
            }
            return length * length;
        }
    }

    /**
     * 动态规划
     */
    public static class Solution2 {
        public int maximalSquare(char[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int[][] dp = new int[m + 1][n + 1];
            int maxSide = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i + 1][j + 1] = matrix[i][j] == '0' ? 0 : 1 + Math.min(Math.min(dp[i][j], dp[i + 1][j]), dp[i][j + 1]);
                    maxSide = Math.max(dp[i + 1][j + 1], maxSide);
                }
            }
            return maxSide * maxSide;
        }
    }
}
