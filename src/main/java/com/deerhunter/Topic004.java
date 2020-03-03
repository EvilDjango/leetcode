package com.deerhunter;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-08
 */
public class Topic004 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int i, j;

        if (0 == m) {
            i = m / 2 + 1;
            j = 0;
        } else if (0 == n) {
            i=0;
            j = n / 2 + 1;
        }else {
            int left = 0, right = m;

            for (; ; ) {
                i = (left + right) / 2;
                j = (m + n + 1) / 2 - i;
                if (isValidIndex(i - 1, m) && isValidIndex(j, n) && nums1[i - 1] > nums2[j]) {
                    right = i - 1;
                    continue;
                }
                if (isValidIndex(i, m) && isValidIndex(j - 1, n) && nums1[i] < nums2[j - 1]) {
                    left = i + 1;
                    continue;
                }
                break;
            }
        }

        double left1Max = Double.NEGATIVE_INFINITY, left2Max = Double.NEGATIVE_INFINITY, right1Min = Double.POSITIVE_INFINITY, right2Min = Double.POSITIVE_INFINITY;
        if (isValidIndex(i - 1, m)) {
            left1Max = nums1[i - 1];
        }
        if (isValidIndex(j - 1, n)) {
            left2Max = nums2[j - 1];
        }
        double leftMax = Math.max(left1Max, left2Max);
        if (0 == (m + n) % 2) {
            if (isValidIndex(i, m)) {
                right1Min = nums1[i];
            }
            if (isValidIndex(j, n)) {
                right2Min = nums2[j];
            }
            double rightMin = Math.min(right1Min, right2Min);
            return (leftMax + rightMin) / 2;
        } else {
            return leftMax;
        }

    }

    private boolean isValidIndex(int index, int length) {
        return index >= 0 && index < length;
    }
}
