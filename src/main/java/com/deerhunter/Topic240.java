package com.deerhunter;

/**
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 * 通过次数140,863提交次数302,942
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/2/21 3:19 PM
 */
public class Topic240 {
    public static class Solution1 {
        public boolean searchMatrix(int[][] matrix, int target) {
            int n = matrix[0].length;
            int r = n;
            for (int[] row : matrix) {
                r = lowerBound(row, r, target);
                if (r < n && row[r] == target) {
                    return true;
                }
            }
            return false;
        }

        public static int lowerBound(int[] nums, int r, int target) {
            int l = 0;
            while (l < r) {
                int mid = (r - l) / 2 + l;
                if (nums[mid] >= target) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }
    }


    /**
     * 持续缩减搜索空间
     */
    public static class Solution2 {
        public boolean searchMatrix(int[][] matrix, int target) {
            return searchRec(matrix, target, 0, matrix.length, 0, matrix[0].length);
        }

        private boolean searchRec(int[][] matrix, int target, int up, int down, int left, int right) {
            if (up == down || left == right) {
                return false;
            }
            if (matrix[up][left] > target || matrix[down - 1][right - 1] < target) {
                return false;
            }
            int mid = (right - left) / 2 + left;
            int row = up;
            while (row < down && matrix[row][mid] <= target) {
                if (matrix[row][mid] == target) {
                    return true;
                }
                row++;
            }
            return searchRec(matrix, target, row, down, left, mid) || searchRec(matrix, target, up, row, mid + 1, right);
        }
    }

    /**
     * 从左下角开始搜索，此时这个矩阵类似一个二叉搜索树
     */
    public static class Solution3 {
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length;
            int n = matrix[0].length;
            int row = m - 1;
            int col = 0;
            while (row >= 0 && col < n) {
                if (matrix[row][col] > target) {
                    row--;
                } else if (matrix[row][col] < target) {
                    col++;
                } else {
                    return true;
                }
            }
            return false;
        }
    }
}
