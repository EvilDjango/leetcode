package com.deerhunter;

import com.deerhunter.common.Utils;

import java.util.Date;
import java.util.TreeMap;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须原地修改，只允许使用额外常数空间。
 * <p>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-05
 */
public class Topic031 {
    /**
     * 看了官方题解后自己写的代码（没有特别明白题意，所以直接看题解了）
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int index = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            int position = Utils.reversedUpperBound(nums, nums[index], index + 1, nums.length);
            Utils.swap(nums, index, position);
        }
        int l = index + 1;
        int r = nums.length - 1;
        while (l < r) {
            Utils.swap(nums, l, r);
            l++;
            r--;
        }
    }

    /**
     * 重刷题目  20220502
     * 这是自己想的解法
     *
     * @param nums
     */
    public static void nextPermutation2(int[] nums) {
        int n = nums.length;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int swapIndex = -1;
        for (int i = n - 1; i >= 0; i--) {
            Integer greater = treeMap.ceilingKey(nums[i] + 1);
            if (greater != null) {
                swap(nums, i, treeMap.get(greater));
                swapIndex = i;
                break;
            }
            treeMap.put(nums[i], i);
        }
        quickSort(nums, swapIndex + 1, nums.length - 1);
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = nums[left];
        int l = left, r = right;
        while (l < r) {
            while (l < r && nums[r] >= pivot) {
                r--;
            }
            swap(nums, l, r);
            while (l < r && nums[l] <= pivot) {
                l++;
            }
            swap(nums, l, r);
        }
        nums[l] = pivot;
        quickSort(nums, left, l - 1);
        quickSort(nums, l + 1, right);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 重刷题目  20220502
     * 参考官方题解
     *
     * @param nums
     */
    public static void nextPermutation3(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = n - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, n - 1);
    }

    private static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left++, right--);
        }
    }
}
