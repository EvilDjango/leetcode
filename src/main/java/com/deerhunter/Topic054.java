package com.deerhunter;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * <p>
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-09
 */
public class Topic054 {
    /**
     * 自己写的算法： 模拟螺旋过程
     */
    public static class Solution1 {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> ans = new ArrayList<>();
            if (matrix.length == 0) {
                return ans;
            }
            boolean[][] visited = new boolean[matrix.length][matrix[0].length];
            spiralOrder(matrix, visited, ans, 0, -1, 0);
            return ans;
        }

        /**
         * 递归地进行螺旋访问
         *
         * @param matrix
         * @param visited
         * @param row
         * @param col
         * @param direction 当前的饿前进方向。0表示从左到右，1表示从上到下，2表示从右到走，3表示从下到上。
         */
        private void spiralOrder(int[][] matrix, boolean[][] visited, List<Integer> ans, int row, int col, int direction) {
            int startRow = row, startCol = col;
            if (direction == 0) {
                while (col + 1 < matrix[row].length && !visited[row][col + 1]) {
                    col++;
                    visited[row][col] = true;
                    ans.add(matrix[row][col]);
                }
            } else if (direction == 1) {
                while (row + 1 < matrix.length && !visited[row + 1][col]) {
                    row++;
                    visited[row][col] = true;
                    ans.add(matrix[row][col]);
                }
            } else if (direction == 2) {
                while (col - 1 >= 0 && !visited[row][col - 1]) {
                    col--;
                    visited[row][col] = true;
                    ans.add(matrix[row][col]);
                }
            } else if (direction == 3) {
                while (row - 1 >= 0 && !visited[row - 1][col]) {
                    row--;
                    visited[row][col] = true;
                    ans.add(matrix[row][col]);
                }
            }

            // 若下标没有变化，说明已经遍历完成
            if (row == startRow && col == startCol) {
                return;
            }
            spiralOrder(matrix, visited, ans, row, col, (direction + 1) % 4);
        }
    }

    /**
     * 参考官方解法写的，可以认为是Solution1的改进版，更加简洁
     */
    public static class Solution2 {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> ans = new ArrayList<>();
            if (matrix.length == 0) {
                return ans;
            }
            int R = matrix.length;
            int C = matrix[0].length;
            boolean[][] visited = new boolean[R][C];
            // dc[i]的值表示当direction为i时，每次移动时列下标的增量
            int[] dc = {1, 0, -1, 0};
            // dr[i]的值表示当direction为i时，每次移动时行下标的增量
            int[] dr = {0, 1, 0, -1};
            // 方向，范围[0,3]
            int direction = 0;
            int row = 0, col = 0;
            while (true) {
                visited[row][col] = true;
                ans.add(matrix[row][col]);
                if (ans.size() == R * C) {
                    break;
                }
                int nextRow = row + dr[direction];
                int nextCol = col + dc[direction];
                if (nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C && !visited[nextRow][nextCol]) {
                    row = nextRow;
                    col = nextCol;
                } else {
                    direction = (direction + 1) % 4;
                    row += dr[direction];
                    col += dc[direction];
                }
            }

            return ans;
        }
    }

    /**
     * 参考官方解法，按层遍历,(r1,c1),(r2,c2)分别是每一层矩形的左上角和右下角。
     * 注意：
     */
    public static class Solution3 {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> ans = new ArrayList<>();
            if (matrix.length == 0) {
                return ans;
            }
            int R = matrix.length;
            int C = matrix[0].length;
            int r1 = 0, r2 = R - 1;
            int c1 = 0, c2 = C - 1;
            // 按层遍历
            while (r1 <= r2 && c1 <= c2) {
                for (int c = c1; c <= c2; c++) {
                    ans.add(matrix[r1][c]);
                }
                for (int r = r1 + 1; r <= r2; r++) {
                    ans.add(matrix[r][c2]);
                }
                if (r1 < r2 && c1 < c2) {
                    for (int c = c2 - 1; c >= c1; c--) {
                        ans.add(matrix[r2][c]);
                    }
                    for (int r = r2 - 1; r > r1; r--) {
                        ans.add(matrix[r][c1]);
                    }
                }
                r1++;
                c1++;
                r2--;
                c2--;
            }
            return ans;
        }
    }
}
