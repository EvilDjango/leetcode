package com.deerhunter.offer;

import java.util.PriorityQueue;

/**
 * 剑指 Offer 40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 * <p>
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 * 通过次数334,211提交次数583,062
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/2 下午5:01
 */
public class Offer040 {
    /**
     * 利用大顶堆
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] ans = new int[k];
        if (k == 0) {
            return ans;
        }
        PriorityQueue<Integer> maxQue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        for (int i : arr) {
            if (maxQue.size() < k) {
                maxQue.add(i);
            } else if (maxQue.peek() > i) {
                maxQue.remove();
                maxQue.add(i);
            }
        }
        for (int i = 0; i < k; i++) {
            ans[i] = maxQue.remove();
        }
        return ans;
    }

    /**
     * 利用快速排序的思想
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        quickSort(arr, 0, arr.length - 1, k);
        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }

    private void quickSort(int[] arr, int left, int right, int k) {
        int base = arr[left];
        int l = left, r = right;
        while (l < r) {
            while (l < r && arr[r] >= base) {
                r--;
            }
            swap(arr, l, r);
            while (l < r && arr[l] <= base) {
                l++;
            }
            swap(arr, l, r);
        }
        arr[l] = base;
        if (l > k) {
            quickSort(arr, left, l - 1, k);
        } else if (l < k - 1) {
            quickSort(arr, l + 1, right, k);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
