package com.deerhunter;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * <p>
 * <p>
 * <p>
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * 说明：m 和 n 的值均不超过 100。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * 通过次数46,342提交次数142,492
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-16
 */
public class Topic063 {
    public static class Solution1 {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if (obstacleGrid.length == 0 || obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
                return 0;
            }

            int rows = obstacleGrid.length;
            int cols = obstacleGrid[0].length;
            int[] row = new int[cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // 起始点特殊处理
                    if (i == 0 && j == 0) {
                        // 如果
                        if (obstacleGrid[0][0] == 1) {
                            return 0;
                        }
                        row[j] = 1;
                    } else if (obstacleGrid[i][j] == 1) {
                        row[j] = 0;
                    } else if (j > 0) {
                        row[j] += row[j - 1];
                    }
                }
            }
            return row[cols - 1];
        }
    }
}
