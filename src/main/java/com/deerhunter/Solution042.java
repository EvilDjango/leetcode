package com.deerhunter;

import com.deerhunter.common.IntStack;

import java.util.Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-11-19
 */
public class Solution042 {
    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int totalC = 0;
        for (int i = 1; i < height.length; i++) {
            int h = height[i];
            int prev = stack.peek();
            int prevH = height[prev];
            // 如果当前高度大于前一个高度，则左侧可能有坑
            if (h > prevH) {
                int left = getPuddleLeft(stack, height, i);
                if (left != -1) {
                    totalC += calCapacity(height, left, i);
                }
            } else if (h == prevH) {
                stack.pop();
            }
            stack.push(i);
        }
        return totalC;
    }

    /**
     * 计算一个坑的存水量，并将相应位置的高度补足到水平面（否则后面会重复计算）
     *
     * @param height
     * @param left
     * @param right
     * @return
     */
    private int calCapacity(int[] height, int left, int right) {
        int total = 0;
        int surface = Math.min(height[left], height[right]);
        for (int i = left + 1; i < right; i++) {
            int capacity = surface - height[i];
            total += capacity;
            height[i] += capacity;
        }
        return total;
    }

    /**
     * 查找左侧的坑
     *
     * @param stack
     * @param heights
     * @param right
     * @return 坑左侧边界下标，若不存在坑，返回-1
     */
    private int getPuddleLeft(Stack<Integer> stack, int[] heights, int right) {
        int height = heights[right];
        stack.pop();
        if (stack.size() == 0) {
            return -1;
        }
        int l = stack.pop();
        while (stack.size() > 0 && heights[l] < height) {
            l = stack.pop();
        }
        if (heights[l] > height) {
            stack.push(l);
        }
        return l;
    }

    /**
     * 参考官方题解写的解法
     */
    static class OfficialSolution {
        public int trap(int[] height) {
            int len = height.length;
            if (len < 2) {
                return 0;
            }
            int[] maxLeft = new int[len];
            maxLeft[0] = height[0];
            for (int i = 1; i < len; i++) {
                maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
            }

            int[] maxRight = new int[len];
            maxRight[len - 1] = height[len - 1];
            for (int i = len - 2; i >= 0; i--) {
                maxRight[i] = Math.max(maxRight[i + 1], height[i]);
            }

            int capacity = 0;
            for (int i = 1; i < len - 1; i++) {
                capacity += Math.min(maxLeft[i], maxRight[i]) - height[i];
            }
            return capacity;
        }

        /**
         * 此解法与我自己的解法{@link Solution042#trap(int[])}本质是一样的，但是我的解法由于分析得不透彻，做了一些无用功，降低了算法
         * 的时间效率
         *
         * @param height
         * @return
         */
        public int trap2(int[] height) {
            if (height.length == 0) {
                return 0;
            }
            Stack<Integer> stack = new Stack<>();
            int totalCapacity = 0;
            for (int i = 0; i < height.length; i++) {
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                    int groove = stack.pop();
                    if (stack.isEmpty()) {
                        break;
                    }
                    int left = stack.peek();
                    int capacity = (Math.min(height[i], height[left]) - height[groove]) * (i - left - 1);
                    totalCapacity += capacity;
                }
                stack.push(i);
            }
            return totalCapacity;
        }

        /**
         * 此解法思路完全等同{@link #trap2(int[])}
         * 区别在于这里使用了数组来模拟栈。因为在leetCode提交代码时，jdk自带的Stack影响了运行效率。
         *
         * @param height
         * @return
         */
        public int trap3(int[] height) {
            if (height.length == 0) {
                return 0;
            }
            IntStack stack = new IntStack(height.length);
            int totalCapacity = 0;
            for (int i = 0; i < height.length; i++) {
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                    int groove = stack.pop();
                    if (stack.isEmpty()) {
                        break;
                    }
                    int left = stack.peek();
                    int capacity = (Math.min(height[i], height[left]) - height[groove]) * (i - left - 1);
                    totalCapacity += capacity;
                }
                stack.push(i);
            }
            return totalCapacity;
        }

        /**
         * 双指针法
         *
         * @param height
         * @return
         */
        public int trap4(int[] height) {
            if (height.length < 3) {
                return 0;
            }
            int left = 0;
            int right = height.length - 1;
            int leftMax = 0;
            int rightMax = 0;
            int totalCapacity = 0;
            while (left <= right) {
                if (leftMax > rightMax) {
                    if (height[right] > rightMax) {
                        rightMax = height[right];
                    }else {
                        totalCapacity += rightMax - height[right];
                    }
                    right--;
                } else {
                    if (height[left] > leftMax) {
                        leftMax = height[left];
                    }else {
                        totalCapacity += leftMax - height[left];
                    }
                    left++;
                }
            }
            return totalCapacity;
        }
    }
}
