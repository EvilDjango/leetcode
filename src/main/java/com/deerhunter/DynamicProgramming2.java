package com.deerhunter;

import java.util.Arrays;

/**
 * 动态规划复习：重新敲一遍
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-12
 */
public class DynamicProgramming2 {
    // 最长子序列问题
    public static void lis() {
        int[] s = {1, 5, 3, 4, 6, 9, 7, 8};
        int[] f = new int[s.length];
        for (int i = 0; i < f.length; i++) {
            f[i] = 1;
        }
        for (int i = 0; i < s.length; i++) {
            for (int j = i + 1; j < s.length; j++) {
                if (s[j] > s[i] && f[j] < f[i] + 1) {
                    f[j] = f[i] + 1;
                }
            }
        }
        print(f);
    }
    private static void print(int[] lcs) {
        System.out.println(Arrays.toString(lcs));
    }
}