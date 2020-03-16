package com.deerhunter;

import com.deerhunter.common.Utils;

import java.math.BigInteger;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 问总共有多少条不同的路径？
 * <p>
 * <p>
 * <p>
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 * <p>
 * 输入: m = 7, n = 3
 * 输出: 28
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10 ^ 9
 * 通过次数80,810提交次数136,086
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-15
 */
public class Topic062 {
    /**
     * 深度遍历，找到所有路径。会超时
     */
    public static class Solution1 {
        public int uniquePaths(int m, int n) {
            int[] pathCount = new int[1];
            findPaths(m, n, 0, 0, pathCount);
            return pathCount[0];
        }

        /**
         * 深度遍历查找可能的路径
         *
         * @param rows      行数
         * @param cols      列数
         * @param row       当前行
         * @param col       当前列
         * @param pathCount 路径数
         */
        private void findPaths(int rows, int cols, int row, int col, int[] pathCount) {
            if (row == rows - 1 && col == cols - 1) {
                pathCount[0]++;
                return;
            }
            if (row < rows - 1) {
                findPaths(rows, cols, row + 1, col, pathCount);
            }
            if (col < cols - 1) {
                findPaths(rows, cols, row, col + 1, pathCount);
            }
        }
    }

    /**
     * 动态规划
     */
    public static class Solution2 {
        public int uniquePaths(int m, int n) {
            // 到达每个位置的可能路径数量
            int[][] dp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i + j == 0) {
                        dp[i][j] = 1;
                        continue;
                    }
                    int leftPaths = i > 0 ? dp[i - 1][j] : 0;
                    int rightPaths = j > 0 ? dp[i][j - 1] : 0;
                    dp[i][j] = leftPaths + rightPaths;
                }
            }
            return dp[m - 1][n - 1];

        }
    }

    /**
     * 排列组合。想法是很不错的，但是为了避免溢出，必须使用BigInteger。
     * <p>
     * 因为机器到底右下角，向下几步，向右几步都是固定的，
     * <p>
     * 比如，m=3, n=2，我们只要向下 1 步，向右 2 步就一定能到达终点。
     * <p>
     * 所以有 C_{m+n-2}^{m-1}C
     * m+n−2
     * m−1
     * ​
     * <p>
     * <p>
     * Python
     * <p>
     * <p>
     * 作者：powcai
     * 链接：https://leetcode-cn.com/problems/unique-paths/solution/dong-tai-gui-hua-by-powcai-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static class Solution3 {
        public int uniquePaths(int m, int n) {
            BigInteger paths = new BigInteger("1", 10);
            for (int i = m + n - 2; i >= n; i--) {
                paths = paths.multiply(new BigInteger("" + i, 10));
            }
            for (int i = 1; i < m; i++) {
                paths = paths.divide(new BigInteger("" + i, 10));
            }
            return Integer.parseInt(paths.toString(10));
        }

        int factorial(int n) {
            if (n < 0) {
                throw new IllegalArgumentException("Negative factorials don't make sense");
            }
            if (n == 0) {
                return 1;
            }
            int product = n;
            int factor = n - 1;
            while (factor > 1) {
                product *= factor--;
            }
            return product;
        }
    }

}
