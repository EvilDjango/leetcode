package com.deerhunter;

import static com.deerhunter.common.Utils.swap;

/**
 * 88. 合并两个有序数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 num1 成为一个有序数组。
 * <p>
 * <p>
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020-04-10 23:50
 */
public class Topic088 {
    /**
     * 将最大的值都放在nums2当中
     */
    public static class Solution1 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            if (n == 0) {
                return;
            }
            for (int i = 0; i < m; i++) {
                if (nums1[i] > nums2[0]) {
                    // 交换值
                    nums1[i] += nums2[0];
                    nums2[0] = nums1[i] - nums2[0];
                    nums1[i] -= nums2[0];
                    for (int j = 0; j + 1 < n && nums2[j] > nums2[j + 1]; j++) {
                        swap(nums2, j, j + 1);
                    }
                }
            }
            for (int i = m, j = 0; i < m + n; i++) {
                nums1[i] = nums2[j++];
            }
        }
    }

    /**
     * 使用额外空间
     */
    public static class Solution2 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int[] all = new int[m + n];
            int i = 0, j = 0, k = 0;
            while (i < m && j < n) {
                if (nums1[i] < nums2[j]) {
                    all[k++] = nums1[i++];
                } else {
                    all[k++] = nums2[j++];
                }
            }
            while (i < m) {
                all[k++] = nums1[i++];
            }
            while (j < n) {
                all[k++] = nums2[j++];
            }
            System.arraycopy(all, 0, nums1, 0, m + n);
        }
    }

    /**
     * 每次添加元素后，将nums1中的元素依次后移
     */
    public static class Solution3 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int i = 0, j = 0, len = m;
            while (j < n) {
                while (i < len && nums1[i] <= nums2[j]) {
                    i++;
                }
                for (int k = len - 1; k >= i; k--) {
                    nums1[k + 1] = nums1[k];
                }
                // 交换值
                nums1[i] += nums2[j];
                nums2[j] = nums1[i] - nums2[j];
                nums1[i] -= nums2[j];
                len++;
                i++;
                j++;
            }
        }
    }

    /**
     * 从后往前排序，既不需要额外的空间，也不需要额外移动元素
     */
    public static class Solution4 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int i = m - 1, j = n - 1, k = m + n - 1;
            while (i >= 0 && j >= 0) {
                if (nums1[i] > nums2[j]) {
                    nums1[k--] = nums1[i--];
                } else {
                    nums1[k--] = nums2[j--];
                }
            }
            while (j >= 0) {
                nums1[k--] = nums2[j--];
            }
        }
    }
}
