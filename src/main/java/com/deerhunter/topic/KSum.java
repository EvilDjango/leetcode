package com.deerhunter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 给定一个数组，从中取k个元素（元素不可重用），k>2(k==1和k==2的情况都有时间复杂度更低的解法).其和为target，返回所有的不重复组合。
 * 这里给出一个使用递归的通用解法：首先排序，然后把k问题分解为一层循环+k-1问题。当k=2时，使用双指针法查找。
 * 最终的时间复杂度是n的k-1次方
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-31
 */
public class KSum {
    private static final List<List<Integer>> result = new ArrayList<>();

    /**
     * 从nums中取k个元素（不可重复使用同一个元素），保证其和为target。返回所有满足条件的组合。
     *
     * @param nums   源数组
     * @param k      每个组合中的元素个数,k>2
     * @param target 每个组合中元素的和
     * @return
     */
    public static List<List<Integer>> kSum(int[] nums, int k, int target) {
        result.clear();
        if (nums == null || nums.length < k) {
            return result;
        }
        Arrays.sort(nums);
        kSum(nums, 0, new int[k - 2], -1, target);
        return result;
    }

    private static void kSum(int[] nums, int start, int[] stack, int stackIndex, int target) {
        // 目前还需要找k个数
        int k = stack.length - stackIndex + 1;
        int len = nums.length;
        if (k > 2) {
            for (int i = start; i < len; i++) {
                // 去重
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }
                stack[stackIndex + 1] = nums[i];
                kSum(nums, i + 1, stack, stackIndex + 1, target - nums[i]);
            }
        } else {
            int left = start;
            int right = len - 1;
            while (left < right) {
                if (nums[left] + nums[right] > target) {
                    right--;
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {
                    List<Integer> combination = new ArrayList<>(k);
                    for (int num : stack) {
                        combination.add(num);
                    }
                    combination.add(nums[left]);
                    combination.add(nums[right]);
                    result.add(combination);
                    left++;
                    right--;
                    // 去重
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
    }
}
