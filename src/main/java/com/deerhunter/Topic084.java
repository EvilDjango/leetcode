package com.deerhunter;

import java.util.HashMap;
import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * 通过次数36,738提交次数93,633
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-04-02
 */
public class Topic084 {
    /**
     * 暴力算法
     */
    public static class Solution1 {
        public int largestRectangleArea(int[] heights) {
            int len = heights.length;
            int maxArea = 0;
            for (int i = 0; i < len; i++) {
                int minHeight = Integer.MAX_VALUE;
                for (int j = i; j < len; j++) {
                    minHeight = Math.min(minHeight, heights[j]);
                    maxArea = Math.max(maxArea, minHeight * (j - i + 1));
                }
            }
            return maxArea;
        }
    }


    /**
     * 分治法
     */
    public static class Solution2 {
        public int largestRectangleArea(int[] heights) {
            return findLargestRectangleArea(heights, 0, heights.length);
        }

        private int findLargestRectangleArea(int[] heights, int left, int right) {
            if (left == right) {
                return 0;
            }

            int lowestPosition = left;
            for (int i = left + 1; i < right; i++) {
                if (heights[i] < heights[lowestPosition]) {
                    lowestPosition = i;
                }
            }
            int max = (right - left) * heights[lowestPosition];
            int leftMax = findLargestRectangleArea(heights, left, lowestPosition);
            int rightMax = findLargestRectangleArea(heights, lowestPosition + 1, right);
            return Math.max(max, Math.max(leftMax, rightMax));
        }
    }

    /**
     * 栈方法
     */
    public static class Solution3 {
        public int largestRectangleArea(int[] heights) {
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            int maxArea = 0;
            for (int i = 0; i < heights.length; i++) {
                while (stack.size() > 1 && heights[stack.peek()] > heights[i]) {
                    int area = heights[stack.pop()] * (i - stack.peek() - 1);
                    maxArea = Math.max(maxArea, area);
                }
                stack.push(i);
            }

            // 处理最末尾的一段升序
            while (stack.size() > 1) {
                int area = heights[stack.pop()] * (heights.length - stack.peek() - 1);
                maxArea = Math.max(maxArea, area);
            }
            return maxArea;
        }
    }


}
