package com.deerhunter.topic;

/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * <p>
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1:
 * <p>
 * 输入:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-27
 */
public class Topic074 {
    public static class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int r = matrix.length;
            if (r == 0) {
                return false;
            }
            int c = matrix[0].length;
            if (c == 0) {
                return false;
            }
            int left = 0;
            int right = r * c;
            while (left < right) {
                int middle = (right - left) / 2 + left;
                int row = middle / c;
                int col = middle % c;
                if (matrix[row][col] > target) {
                    right = middle;
                } else if (matrix[row][col] < target) {
                    left = middle + 1;
                } else {
                    return true;
                }
            }
            return false;
        }
    }
}
