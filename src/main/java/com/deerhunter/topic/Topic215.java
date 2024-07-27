package com.deerhunter.topic;

import java.util.Arrays;
import java.util.Random;

/**
 * 215. Kth Largest Element in an Array
 * Medium
 * <p>
 * 5331
 * <p>
 * 344
 * <p>
 * Add to List
 * <p>
 * Share
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 * <p>
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/3/24 18:23
 */
public class Topic215 {
    /**
     * 暴力算法
     */
    public static class Solution1 {
        public int findKthLargest(int[] nums, int k) {
            Arrays.sort(nums);
            return nums[nums.length - k];
        }
    }


    /**
     * 快速选择
     */
    public static class Solution2 {
        public int findKthLargest(int[] nums, int k) {
            return quickSelect(nums, k, 0, nums.length - 1);
        }

        /**
         * 快速选择，如果在某一次递归中确定了倒数第k个位置的元素，则直接返回该元素
         *
         * @param nums
         * @param k
         * @return
         */
        private int quickSelect(int[] nums, int k, int left, int right) {
            int l = left;
            int r = right;
            while (l < r) {
                while (l < r && nums[r] >= nums[l]) {
                    r--;
                }
                swap(nums, l, r);
                while (l < r && nums[l] <= nums[r]) {
                    l++;
                }
                swap(nums, l, r);
            }
            if (l == nums.length - k) {
                return nums[l];
            } else if (l > nums.length - k) {
                return quickSelect(nums, k, left, l - 1);
            } else {
                return quickSelect(nums, k, l + 1, right);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /**
     * 引入了随机化的快速选择，参考题解
     */
    public static class Solution3 {
        private Random random = new Random();

        public int findKthLargest(int[] nums, int k) {
            return quickSelect(nums, nums.length - k, 0, nums.length - 1);
        }

        private int quickSelect(int[] nums, int index, int left, int right) {
            int i = randomPartition(nums, left, right);
            if (index == i) {
                return nums[i];
            } else if (i < index) {
                return quickSelect(nums, index, i + 1, right);
            } else {
                return quickSelect(nums, index, left, i - 1);
            }
        }

        private int randomPartition(int[] nums, int left, int right) {
            int i = random.nextInt(right - left + 1) + left;
            swap(nums, i, right);
            return partition(nums, left, right);
        }

        private int partition(int[] nums, int left, int right) {
            int base = nums[right];
            int i = left - 1;
            for (int j = left; j < right; j++) {
                if (nums[j] < base) {
                    swap(nums, ++i, j);
                }
            }
            swap(nums, ++i, right);
            return i;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /**
     * 大顶堆
     */
    public static class Solution4 {
        public int findKthLargest(int[] nums, int k) {
            int heapSize = nums.length;
            buildMaxHeap(nums, heapSize);
            for (int i = 0; i < k - 1; i++) {
                swap(nums, 0, heapSize - 1);
                maxHeapify(nums, 0, --heapSize);
            }
            return nums[0];
        }

        private void buildMaxHeap(int[] nums, int heapSize) {
            for (int i = heapSize / 2 - 1; i >= 0; i--) {
                maxHeapify(nums, i, heapSize);
            }
        }

        private void maxHeapify(int[] nums, int i, int heapSize) {
            int l = 2 * i + 1, r = 2 * i + 2, max = i;
            if (l < heapSize && nums[l] > nums[max]) {
                max = l;
            }
            if (r < heapSize && nums[r] > nums[max]) {
                max = r;
            }
            if (i != max) {
                swap(nums, i, max);
                maxHeapify(nums, max, heapSize);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
