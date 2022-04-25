package com.deerhunter;

import java.util.Arrays;

/**
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,5,6,4]
 * 输出: 5
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= 数组长度 <= 50000
 * <p>
 * 通过次数139,388提交次数284,055
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/25 下午7:43
 */
public class Offer051 {
    /**
     * 归并排序
     *
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        int n = nums.length;
        int count = 0;
        int[] temp = new int[n];
        for (int size = 1; size < n; size <<= 1) {
            for (int i = 0; i < n; i += 2 * size) {
                count += mergeAndCount(nums, temp, i, i + size, i + 2 * size);
            }
        }
        return count;
    }

    private int mergeAndCount(int[] nums, int[] temp, int left, int mid, int right) {
        if (mid >= nums.length) {
            return 0;
        }
        if (right > nums.length) {
            right = nums.length;
        }

        int i1 = left, i2 = mid, i3 = 0;
        int count = 0;
        while (i1 < mid && i2 < right) {
            if (nums[i1] <= nums[i2]) {
                temp[i3++] = nums[i1++];
                count += i2 - mid;
            } else {
                temp[i3++] = nums[i2++];
            }
        }
        while (i1 < mid) {
            temp[i3++] = nums[i1++];
            count += i2 - mid;
        }
        while (i2 < right) {
            temp[i3++] = nums[i2++];
        }

        System.arraycopy(temp, 0, nums, left, right - left);
        return count;
    }

    /**
     * 树状数组
     *
     * @param nums
     * @return
     */
    public int reversePairs2(int[] nums) {
        int n = nums.length;
        int[] temp = new int[n];
        System.arraycopy(nums, 0, temp, 0, n);
        Arrays.sort(temp);
        for (int i = 0; i < n; i++) {
            nums[i] = Arrays.binarySearch(temp, nums[i]) + 1;
        }
        BIT bit = new BIT(n);
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            ans += bit.query(nums[i] - 1);
            bit.update(nums[i], 1);
        }
        return ans;
    }

    /**
     * 树状数组
     */
    private class BIT {
        private int[] tree;
        private int size;

        public BIT(int size) {
            this.tree = new int[size + 1];
            this.size = size;
        }

        private int lowBit(int x) {
            return x & (-x);
        }

        public void update(int x, int v) {
            while (x <= size) {
                tree[x] += v;
                x += lowBit(x);
            }
        }

        public int query(int x) {
            int sum = 0;
            while (x != 0) {
                sum += tree[x];
                x -= lowBit(x);
            }
            return sum;
        }
    }
}
