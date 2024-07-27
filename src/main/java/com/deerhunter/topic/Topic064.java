package com.deerhunter.topic;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-16
 */
public class Topic064 {
    public static class Solution1 {
        public int minPathSum(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i > 0 && j > 0) {
                        grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
                    } else if (i > 0) {
                        grid[i][j] += grid[i - 1][j];
                    } else if ( j > 0) {
                        grid[i][j] += grid[i][j - 1];
                    }
                }
            }
            return grid[rows - 1][cols - 1];
        }
    }
}
