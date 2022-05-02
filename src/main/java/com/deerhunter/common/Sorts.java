package com.deerhunter.common;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多种排序算法
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-19
 */
public class Sorts {

    public static void shellSort(int[] nums) {
        int len = nums.length;
        for (int step = len / 2; step >= 1; step /= 2) {
            for (int i = step; i < len; i += step) {
                for (int j = i; j >= step && nums[j - step] > nums[j]; j -= step) {
                    swap(nums, j - step, j);
                }
            }
        }
    }

    public static void bucketSortRecursive(int[] nums) {
        int nBucket = (int) (Math.log(nums.length) / Math.log(2));
        int[][] buckets = new int[nBucket][];
        for (int i = 0; i < nBucket; i++) {
            buckets[i] = new int[nums.length / nBucket + 1];
        }
    }

    /**
     * 快速排序
     *
     * @param nums
     */
    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    /**
     * 快速排序，将数组的[left,right]区间进行排序
     *
     * @param nums
     * @param left
     * @param right
     */
    private static void quickSort(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        int base = nums[left];
        int l = left;
        int r = right;
        while (l < r) {
            while (l < r && nums[r] >= base) {
                r--;
            }
            while (l < r && nums[l] < base) {
                l++;
            }
            if (l < r) {
                swap(nums, l, r);
            }
        }
        swap(nums, left, l);
        quickSort(nums, left, l);
        quickSort(nums, l + 1, right);
    }


    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * 堆排序
     *
     * @param nums
     */
    public static void heapSort(int[] nums) {
        convertToMaximumHeap(nums);
        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, 0, i);
            siftDown(nums, i, 0);
        }
    }

    /**
     * 将二叉树转换为最大堆
     *
     * @param binaryTree 二叉树
     */
    private static void convertToMaximumHeap(int[] binaryTree) {
        for (int i = binaryTree.length / 2 - 1; i >= 0; i--) {
            siftDown(binaryTree, binaryTree.length, i);
        }
    }


    /**
     * 向下调节最大堆中指定元素的位置（如果不满足最大堆定义的话）
     *
     * @param maximumHeap
     * @param len
     * @param indexToAdjust
     */
    private static void siftDown(int[] maximumHeap, int len, int indexToAdjust) {
        int leftChild;
        int maxIndex;
        while ((leftChild = indexToAdjust * 2 + 1) < len) {
            if (maximumHeap[leftChild] > maximumHeap[indexToAdjust]) {
                maxIndex = leftChild;
            } else {
                maxIndex = indexToAdjust;
            }
            int rightChild = leftChild + 1;
            if (rightChild < len && maximumHeap[rightChild] > maximumHeap[maxIndex]) {
                maxIndex = rightChild;
            }

            if (maxIndex == indexToAdjust) {
                return;
            } else {
                swap(maximumHeap, maxIndex, indexToAdjust);
                indexToAdjust = maxIndex;
            }
        }
    }
}
