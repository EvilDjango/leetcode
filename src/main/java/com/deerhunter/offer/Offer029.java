package com.deerhunter.offer;

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 * <p>
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 * 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
 * <p>
 * 通过次数193,384提交次数441,974
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/7 下午7:28
 */
public class Offer029 {
    /**
     * 自己写的解法，比较丑陋
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return new int[0];
        }
        int n = matrix[0].length;
        int[] ans = new int[m * n];
        int index = 0;
        int shuttles = Math.min((m + 1) / 2, (n + 1) / 2);
        int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int i = 0; i < shuttles; i++) {
            int x = i, y = i;

            // 中间只剩一行的时候特殊处理
            if ((m & 1) == 1 && i == m / 2) {
                for (; y < n - i; y++) {
                    ans[index++] = matrix[x][y];
                }
                break;
            }
            // 中间只剩一列的时候特殊处理
            if ((n & 1) == 1 && i == n / 2) {
                for (; x < m - i; x++) {
                    ans[index++] = matrix[x][y];
                }
                break;
            }
            for (int j = 0; j < 4; j++) {
                int length = (j & 1) == 0 ? n - (i << 1) : m - (i << 1);
                for (int k = 0; k < length - 1; k++) {
                    ans[index++] = matrix[x][y];
                    x += dir[j][0];
                    y += dir[j][1];
                }
            }
        }
        return ans;
    }

    /**
     * 模拟，参考官方题解
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int total = rows * cols;
        int[] ans = new int[total];
        int index = 0;
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dirIndex = 0;
        int row = 0, col = 0;
        for (int i = 0; i < total; i++) {
            ans[index++] = matrix[row][col];
            visited[row][col] = true;
            int nextRow = row + dirs[dirIndex][0];
            int nextCol = col + dirs[dirIndex][1];
            if (nextRow < 0 || nextRow == rows || nextCol < 0 || nextCol == cols || visited[nextRow][nextCol]) {
                dirIndex = (dirIndex + 1) % 4;
                nextRow = row + dirs[dirIndex][0];
                nextCol = col + dirs[dirIndex][1];
            }
            row = nextRow;
            col = nextCol;
        }
        return ans;
    }

    /**
     * 按层模拟，参考官方题解
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder3(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, cols = matrix[0].length;
        int left = 0, right = cols - 1, top = 0, bottom = rows - 1;
        int[] order = new int[rows * cols];
        int index = 0;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                order[index++] = matrix[top][i];
            }
            for (int i = top + 1; i <= bottom; i++) {
                order[index++] = matrix[i][right];
            }
            if (left < right && top < bottom) {
                for (int i = right - 1; i >= left; i--) {
                    order[index++] = matrix[bottom][i];
                }
                for (int i = bottom - 1; i > top; i--) {
                    order[index++] = matrix[i][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}
