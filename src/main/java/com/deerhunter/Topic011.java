package com.deerhunter;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-26
 */
public class Topic011 {
    public int maxArea(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxArea = Math.max(Math.min(height[i], height[j]) * (j - i), maxArea);
            }
        }
        return maxArea;
    }

    public int maxArea2(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            maxArea = Math.max(Math.min(height[left], height[right]) * (right - left), maxArea);
            int l = left;
            if (height[left] <= height[right]) {
                left++;
            }
            if (height[l] >= height[right]) {
                right--;
            }
        }
        return maxArea;
    }

    public static class Solution2 {
        public int maxArea(int[] height) {
            int left = 0;
            int right = height.length - 1;
            int maxArea = 0;
            while (left < right) {
                int area = (right - left) * Math.min(height[left], height[right]);
                maxArea = Math.max(area, maxArea);
                if (height[left] < height[right]) {
                    int lastLeft = left;
                    while (left < right && height[left] <= height[lastLeft]) {
                        left++;
                    }
                } else {
                    int lastRight = right;
                    while (left < right && height[right] <= height[lastRight]) {
                        right--;
                    }
                }
            }
            return maxArea;
        }

    }
}
