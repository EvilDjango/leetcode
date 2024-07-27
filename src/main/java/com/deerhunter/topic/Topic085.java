package com.deerhunter.topic;

import com.deerhunter.common.IntStack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * 输出: 6
 * 通过次数22,706提交次数50,432
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-04-02
 */
public class Topic085 {
    /**
     * 暴力算法
     */
    public static class Solution1 {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length;
            int n;
            if (m == 0 || (n = matrix[0].length) == 0) {
                return 0;
            }
            int maxArea = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    maxArea = Math.max(maxArea, maximalRectangle(matrix, i, j));
                }
            }
            return maxArea;
        }

        /**
         * 计算以特定点为左上角的最大矩形的面积
         *
         * @param matrix
         * @param row
         * @param col
         * @return
         */
        private int maximalRectangle(char[][] matrix, int row, int col) {
            char c = '1';
            // 列右界，不包含
            int maxCol = matrix[0].length;
            int maxArea = 0;
            for (int i = row; i < matrix.length; i++) {
                int j = col;
                while (j < maxCol && matrix[i][j] == c) {
                    j++;
                }
                // 这个break可以省略，但是增加这条语句可以减少几次空循环，略微提升性能
                if (j == col) {
                    break;
                }
                //截止当前行的最大面积
                int area = (i - row + 1) * (j - col);
                maxArea = Math.max(maxArea, area);
                // 更新列右界
                maxCol = j;
            }
            return maxArea;
        }
    }

    /**
     * 将此问题转换为多个84题的组合
     */
    public static class Solution2 {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length;
            int n;
            if (m == 0 || (n = matrix[0].length) == 0) {
                return 0;
            }
            char c = '1';
            // 记录每个位置的高度，高度是指从这个位置向下的连续的'1'的长度
            int[][] height = new int[m][n];

            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (matrix[i][j] == c) {
                        height[i][j] = i == m - 1 ? 1 : height[i + 1][j] + 1;
                    }
                }
            }

            int maxArea = 0;
            for (int row = 0; row < m; row++) {
                // 从当前行往下可能得到的最大矩形面积
                int potentialMax = (m - row) * n;
                if (maxArea >= potentialMax) {
                    continue;
                }
                Stack<Integer> stack = new Stack<>();
                stack.push(-1);
                for (int col = 0; col < n; col++) {
                    if (col > 0 && height[row][col] < height[row][col - 1]) {
                        while (stack.size() > 1 && height[row][stack.peek()] > height[row][col]) {
                            int area = height[row][stack.pop()] * (col - stack.peek() - 1);
                            maxArea = Math.max(maxArea, area);
                        }
                    }
                    stack.push(col);
                }

                // 处理最后一段升序
                while (stack.size() > 1) {
                    int area = height[row][stack.pop()] * (n - stack.peek() - 1);
                    maxArea = Math.max(maxArea, area);
                }

            }

            return maxArea;
        }
    }

    /**
     * 解法2的优化版
     */
    public static class Solution3 {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length;
            int n;
            if (m == 0 || (n = matrix[0].length) == 0) {
                return 0;
            }
            int[] height = new int[n];
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            int maxArea = 0;
            for (char[] row : matrix) {
                for (int col = 0; col < n; col++) {
                    if (row[col] == '1') {
                        height[col]++;
                    } else {
                        height[col] = 0;
                    }
                    while (stack.size() > 1 && height[stack.peek()] >= height[col]) {
                        int area = height[stack.pop()] * (col - stack.peek() - 1);
                        maxArea = Math.max(maxArea, area);
                    }
                    stack.push(col);
                }

                // 处理最后一段升序
                while (stack.size() > 1) {
                    int area = height[stack.pop()] * (n - stack.peek() - 1);
                    maxArea = Math.max(maxArea, area);
                }
            }
            return maxArea;
        }
    }

    /**
     * 优化的暴力算法:分别计算以每个坐标为右下角的矩形的最大面积
     */
    public static class Solution4 {
        public int maximalRectangle(char[][] matrix) {
            int rows = matrix.length;
            if (rows == 0) {
                return 0;
            }
            int cols = matrix[0].length;
            if (cols == 0) {
                return 0;
            }

            // 每一列的最大高度
            int[] height = new int[cols];
            int maxArea = 0;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (matrix[row][col] == '1') {
                        height[col]++;
                    } else {
                        height[col] = 0;
                    }
                    int minHeight = Integer.MAX_VALUE;
                    for (int i = col; i >= 0; i--) {
                        minHeight = Math.min(minHeight, height[i]);
                        int area = (col - i + 1) * minHeight;
                        maxArea = Math.max(maxArea, area);
                        // 遇到一个高度为0的柱子之后，后面的最大面积都为0，直接跳过
                        if (minHeight == 0) {
                            break;
                        }
                    }
                }
            }
            return maxArea;
        }
    }

    /**
     * 动态规划 - 每个点的最大高度
     */
    public static class Solution5 {
        public int maximalRectangle(char[][] matrix) {
            int rows = matrix.length;
            if (rows == 0) {
                return 0;
            }
            int cols = matrix[0].length;
            if (cols == 0) {
                return 0;
            }
            int[] height = new int[cols];
            int[] left = new int[cols];
            int[] right = new int[cols];
            Arrays.fill(right, cols);
            int maxArea = 0;
            for (char[] row : matrix) {
                for (int j = 0; j < cols; j++) {
                    if (row[j] == '1') {
                        height[j]++;
                    } else {
                        height[j] = 0;
                    }
                }
                int curLeft = 0;
                for (int j = 0; j < cols; j++) {
                    if (row[j] == '1') {
                        left[j] = Math.max(left[j], curLeft);
                    } else {
                        left[j] = 0;
                        curLeft = j + 1;
                    }
                }
                int curRight = cols;
                for (int j = cols - 1; j >= 0; j--) {
                    if (row[j] == '1') {
                        right[j] = Math.min(right[j], curRight);
                    } else {
                        right[j] = cols;
                        curRight = j;
                    }
                }
                for (int j = 0; j < cols; j++) {
                    int area = (right[j] - left[j]) * height[j];
                    maxArea = Math.max(maxArea, area);
                }
            }
            return maxArea;
        }
    }

    /**
     * 解法2的优化版：使用了自定义的栈，试图提升性能
     * <p>
     * 事实证明，栈算法确实是最优雅性能也最佳的算法。
     * 但是使用jdk的 Stack类带来的性能开销会导致在leetCode提交时效率大大降低
     */
    public static class Solution6 {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length;
            int n;
            if (m == 0 || (n = matrix[0].length) == 0) {
                return 0;
            }
            int[] height = new int[n];
            int[] stack = new int[n + 1];
            int top = 0;
            stack[top++] = -1;
            int maxArea = 0;
            for (char[] row : matrix) {
                for (int col = 0; col < n; col++) {
                    if (row[col] == '1') {
                        height[col]++;
                    } else {
                        height[col] = 0;
                    }
                    while (top > 1 && height[stack[top - 1]] >= height[col]) {
                        int area = height[stack[--top]] * (col - stack[top - 1] - 1);
                        maxArea = Math.max(maxArea, area);
                    }
                    stack[top++] = col;
                }

                // 处理最后一段升序
                while (top > 1) {
                    int area = height[stack[--top]] * (n - stack[top - 1] - 1);
                    maxArea = Math.max(maxArea, area);
                }
            }
            return maxArea;
        }
    }

    /**
     * 同解法3， 使用了自定义的栈对象
     */
    public static class Solution7 {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length;
            int n;
            if (m == 0 || (n = matrix[0].length) == 0) {
                return 0;
            }
            int[] height = new int[n];
            IntStack stack = new IntStack(n + 1);
            stack.push(-1);
            int maxArea = 0;
            for (char[] row : matrix) {
                for (int col = 0; col < n; col++) {
                    if (row[col] == '1') {
                        height[col]++;
                    } else {
                        height[col] = 0;
                    }
                    while (stack.size() > 1 && height[stack.peek()] >= height[col]) {
                        int area = height[stack.pop()] * (col - stack.peek() - 1);
                        maxArea = Math.max(maxArea, area);
                    }
                    stack.push(col);
                }

                // 处理最后一段升序
                while (stack.size() > 1) {
                    int area = height[stack.pop()] * (n - stack.peek() - 1);
                    maxArea = Math.max(maxArea, area);
                }
            }
            return maxArea;
        }
    }
}
