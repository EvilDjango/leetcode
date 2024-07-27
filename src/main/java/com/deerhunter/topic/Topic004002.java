package com.deerhunter.topic;

import java.util.Arrays;

/**
 * 第4题的第二种解法
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-08
 */
public class Topic004002 {
    public double findMedianSortedArrays(int[] a, int[] b) {
        // 确保b的长度大于a
        if (a.length > b.length) {
            int[] temp = a;
            a = b;
            b = temp;
        }
        int m = a.length;
        int n = b.length;
        int left = 0;
        int right = m;
        while (left <= right) {
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;
            if (i < m && a[i] < b[j - 1]) {
                left = i + 1;
                continue;
            }
            if (i > 0 && a[i - 1] > b[j]) {
                right = i - 1;
                continue;
            }
            double maxLeft;
            if (0 == i) {
                maxLeft = b[j - 1];
            } else if (0 == j) {
                maxLeft = a[i - 1];
            } else {
                maxLeft = Math.max(a[i - 1], b[j - 1]);
            }
            if (1 == (m + n) % 2) {
                return maxLeft;
            }

            double minRight;
            if (m == i) {
                minRight = b[j];
            } else if (n == j) {
                minRight = a[i];
            } else {
                minRight = Math.min(a[i], b[j]);
            }

            return (maxLeft + minRight) / 2.0;
        }
        return 0.0;
    }

    public double getMedian(int[] a, int[] b) {
        int totalLen = a.length + b.length;
        if (0 == totalLen) {
            return 0.0;
        }
        int[] all = new int[totalLen];
        System.arraycopy(a, 0, all, 0, a.length);
        System.arraycopy(b, 0, all, a.length, b.length);
        Arrays.sort(all);
        if (1 == totalLen % 2) {
            return all[totalLen / 2];
        }
        return (all[totalLen / 2] + all[totalLen / 2 - 1]) / 2.0;
    }
}
