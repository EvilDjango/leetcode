package com.deerhunter;

import java.util.HashMap;
import java.util.Map;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * <p>
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * <p>
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/12/24 10:02
 */
public class Topic167 {
    public static class Solution1 {
        public int[] twoSum(int[] numbers, int target) {
            Map<Integer, Integer> visited = new HashMap<>();
            for (int i = 0; i < numbers.length; i++) {
                int number = numbers[i];
                int rival = target - number;
                if (visited.containsKey(rival)) {
                    return new int[]{visited.get(rival) + 1, i + 1};
                }
                visited.put(number, i);
            }
            return new int[]{-1, -1};
        }
    }

    /**
     * 二分查找
     */
    public static class Solution2 {
        public int[] twoSum(int[] numbers, int target) {
            for (int i = 0; i < numbers.length - 1; i++) {
                int rival = target - numbers[i];
                int left = i + 1;
                int right = numbers.length;
                while (left < right) {
                    int mid = (right - left) / 2 + left;
                    if (numbers[mid] > rival) {
                        right = mid;
                    } else if (numbers[mid] < rival) {
                        left = mid + 1;
                    } else {
                        return new int[]{i + 1, mid + 1};
                    }
                }
            }
            return new int[]{-1, -1};
        }
    }

    /**
     * 双指针法
     */
    public static class Solution3 {
        public int[] twoSum(int[] numbers, int target) {
            int left = 0;
            int right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[left] + numbers[right];
                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                }else {
                    return new int[]{left + 1, right + 1};
                }
            }
            return new int[]{-1, -1};
        }
    }
}
